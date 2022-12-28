package vitec.sureservice.data.remote

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import vitec.sureservice.data.model.Reservation
import vitec.sureservice.data.model.ServiceRequest
import vitec.sureservice.data.model.Technician
import vitec.sureservice.ui.reservation.ReservationDto

interface ReservationInterface {

    @POST("reservations/{serviceRequestId}")
    fun postReservation(@Path ("serviceRequestId") serviceRequestId: Int, @Body reservationDto: ReservationDto): Call<Reservation>

}