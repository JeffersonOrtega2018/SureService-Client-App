package vitec.sureservice.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import vitec.sureservice.data.model.Client
import vitec.sureservice.ui.login.LoginDto
import vitec.sureservice.ui.settings.SettingsDto
import vitec.sureservice.ui.settings.UpdatePasswordDto
import vitec.sureservice.ui.signup.SignupDto

interface ClientInterface {

    @POST("users/auth/sign-in")
    fun loginClient(@Body loginDto: LoginDto): Call<Client>

    @POST("clients/sign-up")
    fun signupClient(@Body signupDto: SignupDto): Call<Client>


    @GET("clients/{clientId}")
    fun getClientById(@Path("clientId") clientId: Long): Call<Client>

    @PUT("clients/{clientId}")
    fun updateClient(@Path("clientId") clientId: Long, @Body settingsDto: SettingsDto): Call<SettingsDto>

    @PUT("users/password/{userId}")
    fun updatePassword(@Path("userId") userId: Long, @Body updatePasswordDto: UpdatePasswordDto): Call<UpdatePasswordDto>

}