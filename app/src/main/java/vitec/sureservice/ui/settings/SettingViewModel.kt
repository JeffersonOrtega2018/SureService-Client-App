package vitec.sureservice.ui.settings

import android.app.Application
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import vitec.sureservice.R
import vitec.sureservice.data.local.SureServiceDatabase
import vitec.sureservice.data.model.Client
import vitec.sureservice.data.remote.ApiClient
import vitec.sureservice.navigation.Destinations


class SettingViewModel(application: Application): AndroidViewModel(application) {
    val state: MutableState<SettingState> = mutableStateOf(SettingState())
    val clientDao = SureServiceDatabase.getInstance(application).clientDao()
    val settingInterface = ApiClient.build()
    var client = Client()

    init {
        getAllClients()
    }

    fun getAllClients() {
        val _client = clientDao.getAllClients()[0]

        getClientById(_client.id)

    }

    fun getClientById(clientId: Long){
        viewModelScope.launch {
            try {
                val getClientById = settingInterface.getClientById(clientId)

                getClientById.enqueue(object : Callback<Client>{
                    override fun onResponse(call: Call<Client>, response: Response<Client>) {
                        if(response.code() == 200){
                            client = response.body()!!
                        }
                    }

                    override fun onFailure(call: Call<Client>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

            } catch(_: Exception){

            }
        }
    }

    fun updatePassword(
        userId: Long,
        password: String,
        confirmPassword: String,
        navController: NavHostController
    ){

        val updatePasswordDto = UpdatePasswordDto(password, confirmPassword)


        val errorMessage = if(updatePasswordDto.confirmPassword.isEmpty() or  updatePasswordDto.newPassword.isEmpty()){
            R.string.error_missing_information
        } else if(updatePasswordDto.newPassword != updatePasswordDto.confirmPassword) {
            R.string.error_incorrectly_repeated_password
        } else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = errorMessage)
            return
        }

        viewModelScope.launch {
            try {
                val updatePassword = settingInterface.updatePassword(userId, updatePasswordDto)

                updatePassword.enqueue(object: Callback<UpdatePasswordDto>{
                    override fun onResponse(
                        call: Call<UpdatePasswordDto>,
                        response: Response<UpdatePasswordDto>
                    ) {
                        navController.navigate(Destinations.Settings.route) {
                            popUpTo(Destinations.Settings.route){
                                inclusive = true
                            }
                        }
                    }

                    override fun onFailure(call: Call<UpdatePasswordDto>, t: Throwable) {

                    }

                })

            }catch (_: Exception){

            }
        }

    }

    fun updateClient(
        client: Client,
        email: String,
        username: String,
        name: String,
        lastName: String,
        telephoneNumber: String,
        dni: String,
        navController: NavHostController
    ){
        val settingsDto = SettingsDto(client.username, client.email, client.name, client.last_name, client.telephone_number, client.dni, 0)


        if(email.isNotEmpty()){
            settingsDto.email = email
        }
        if(username.isNotEmpty()){
            settingsDto.username = username
        }
        if(name.isNotEmpty()){
            settingsDto.name = name
        }
        if(lastName.isNotEmpty()){
            settingsDto.last_name = lastName
        }
        if(telephoneNumber.isNotEmpty()){
            settingsDto.telephone_number = telephoneNumber
        }
        if(dni.isNotEmpty()){
            settingsDto.dni = dni
        }

        val errorMessage = if(!Patterns.EMAIL_ADDRESS.matcher(settingsDto.email).matches()){
                R.string.error_not_a_valid_email
        } else if(!Patterns.PHONE.matcher(settingsDto.telephone_number).matches()) {
            R.string.error_not_a_valid_phone_number
        }  else if(settingsDto.dni.length != 8){
            R.string.error_incorrectly_dni
        } else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = errorMessage)
            return
        }

        viewModelScope.launch {
            try {
                val updateClient = settingInterface.updateClient( client.id, settingsDto )

                updateClient.enqueue(object: Callback<SettingsDto>{
                    override fun onResponse(call: Call<SettingsDto>, response: Response<SettingsDto>) {
                        navController.navigate(Destinations.Settings.route) {
                            popUpTo(Destinations.Settings.route){
                                inclusive = true
                            }
                        }
                    }

                    override fun onFailure(call: Call<SettingsDto>, t: Throwable) {

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