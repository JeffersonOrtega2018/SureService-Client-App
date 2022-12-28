package vitec.sureservice.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import vitec.sureservice.data.model.ServiceRequest
import vitec.sureservice.ui.bookAnAppointment.ServiceRequestDto
import vitec.sureservice.ui.reservation.ServiceRequestPutDto

interface ServiceRequestInterface {

    @POST("services/{clientId}/{technicianId}")
    fun postServiceRequest(@Path("clientId") clientId: Int, @Path("technicianId") technicianId: Int, @Body serviceRequestDto: ServiceRequestDto): Call<ServiceRequest>

    @GET("services/client/{clientId}")
    fun getServiceRequestByClientId(@Path("clientId") clientId: Int): Call<List<ServiceRequest>>

    @GET("services/{serviceRequestId}")
    fun getServiceRequestById(@Path("serviceRequestId") serviceRequestId: Int): Call<ServiceRequest>

    @PUT("services/{serviceRequestId}")
    fun putServiceRequestById( @Path("serviceRequestId") serviceRequestId: Int, @Body serviceRequestPutDto: ServiceRequestPutDto): Call<ServiceRequest>

}