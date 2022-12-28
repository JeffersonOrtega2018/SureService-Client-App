package vitec.sureservice.ui.signup

import android.app.Application
import android.util.Log
import kotlinx.coroutines.launch
import android.util.Patterns
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

class SignUpViewModel(application: Application): AndroidViewModel(application) {
    val state: MutableState<SignUpState> = mutableStateOf(SignUpState())
    val clientDao = SureServiceDatabase.getInstance(application).clientDao()
    val signupInterface = ApiClient.build()

    fun insertClient(client: Client) {
        clientDao.insertClient(client)
    }


    fun signup(
        email: String,
        password: String,
        username: String,
        name: String,
        lastName: String,
        telephoneNumber: String,
        dni: String,
        confirmPassword: String
    ) {
        val errorMessage = if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            R.string.error_not_a_valid_email
        } else if(!Patterns.PHONE.matcher(telephoneNumber).matches()) {
            R.string.error_not_a_valid_phone_number
        } else if(password != confirmPassword) {
            R.string.error_incorrectly_repeated_password
        } else if(dni.length != 8){
            R.string.error_incorrectly_dni
        } else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = errorMessage)
            return
        }

        viewModelScope.launch {
            try {
                val signup = signupInterface.signupClient(SignupDto(username, email, password, name, lastName, telephoneNumber, dni))

                signup.enqueue(object: Callback<Client>{
                    override fun onResponse(call: Call<Client>, response: Response<Client>) {

                        if(response.code() == 201) {
                            val client = Client(response.body()!!.id, response.body()!!.username)
                            insertClient(client)
                            state.value = state.value.copy(successSignUp = true)
                        }

                        if(response.code() == 400) {
                            Log.println(Log.WARN, "SignUp", response.body()!!.toString())
                        }
                    }

                    override fun onFailure(call: Call<Client>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

            } catch (_: Exception){

            }

        }
    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }
}