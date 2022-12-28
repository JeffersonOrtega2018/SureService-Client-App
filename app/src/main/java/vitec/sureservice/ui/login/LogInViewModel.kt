package vitec.sureservice.ui.login

import android.app.Application
import kotlinx.coroutines.launch
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vitec.sureservice.R
import vitec.sureservice.data.local.SureServiceDatabase
import vitec.sureservice.data.model.Client
import vitec.sureservice.data.remote.ApiClient

class LogInViewModel(application: Application): AndroidViewModel(application) {

    val state: MutableState<LogInState> = mutableStateOf(LogInState())
    val clientDao = SureServiceDatabase.getInstance(application).clientDao()
    val loginInterface = ApiClient.build()
    var clients = emptyList<Client>()
    var client = Client()

    var isFailure = false
    var isBadCredentials = false

    init {
        getAllClients()
    }

    fun getAllClients() {
        clients = clientDao.getAllClients()

        if(clients.isNotEmpty()){
            state.value.successLogIn = true
            client.id = clients[0].id
            client.username = clients[0].username
        }
    }

    fun insertClient(client: Client) {
        clientDao.insertClient(client)
    }

    fun login(username: String, password: String) {


        viewModelScope.launch {
            try {
                val login = loginInterface.loginClient(LoginDto(username, password))

                login.enqueue(object: Callback<Client>{
                    override fun onResponse(call: Call<Client>, response: Response<Client>) {

                        if(response.code() == 200) {

                            if(response.body()?.roles?.get(0) != "ROLE_CLIENT"){
                                isFailure = true
                            } else{

                                isFailure = false
                                client.id = response.body()!!.id
                                client.username = response.body()!!.username
                                insertClient(client)
                                state.value = state.value.copy(successLogIn = true)
                            }

                        }

                        if(response.code() == 400) {
                            isFailure = true
                        }

                        val errorMessage = if(isFailure){
                            R.string.error_invalid_credentials
                        } else if(isBadCredentials){
                            R.string.error_unknown
                        } else null

                        errorMessage?.let {
                            state.value = state.value.copy(errorMessage = it)
                            return
                        }

                    }

                    override fun onFailure(call: Call<Client>, t: Throwable) {
                        isBadCredentials = true
                    }

                })
            } catch(_: Exception){

            }
        }

    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }


}