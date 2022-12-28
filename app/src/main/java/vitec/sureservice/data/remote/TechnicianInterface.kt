package vitec.sureservice.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import vitec.sureservice.data.model.Technician
import vitec.sureservice.ui.reservation.TechnicianDto

interface TechnicianInterface {
    @GET("technician")
    fun getAllTechnicians(): Call<List<Technician>>

    @GET("technician/speciality/{specialityId}")
    fun getTechniciansBySpeciality(@Path("specialityId") specialityId: Int): Call<List<Technician>>

    @GET("technician/{id}")
    fun getTechnicianById(@Path("id") id: Int): Call<Technician>

    @PUT("technician/{technicianId}")
    fun editTechnician(@Path("technicianId") technicianId: Int, @Body technicianDto: TechnicianDto): Call<Technician>
}