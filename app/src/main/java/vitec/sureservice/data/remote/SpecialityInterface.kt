package vitec.sureservice.data.remote

import retrofit2.Call
import retrofit2.http.GET
import vitec.sureservice.data.model.Speciality

interface SpecialityInterface {
    @GET("speciality")
    fun getAllSpecialities(): Call<List<Speciality>>
}