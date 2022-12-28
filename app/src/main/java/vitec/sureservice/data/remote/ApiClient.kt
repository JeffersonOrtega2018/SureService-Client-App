package vitec.sureservice.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_BASE_URL = "https://sure-service.herokuapp.com/api/v1/"

    private var clientInterface: ClientInterface? = null
    private var technicianInterface: TechnicianInterface? = null
    private var serviceRequestInterface: ServiceRequestInterface? = null
    private var specialityInterface: SpecialityInterface? = null
    private var reservationInterface: ReservationInterface? = null

    fun build(): ClientInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        clientInterface = retrofit.create(ClientInterface::class.java)
        return clientInterface as ClientInterface
    }

    fun buildTechnician(): TechnicianInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        technicianInterface = retrofit.create(TechnicianInterface::class.java)
        return technicianInterface as TechnicianInterface
    }

    fun buildServiceRequest(): ServiceRequestInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        serviceRequestInterface = retrofit.create(ServiceRequestInterface::class.java)
        return serviceRequestInterface as ServiceRequestInterface
    }

    fun buildSpeciality(): SpecialityInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        specialityInterface = retrofit.create(SpecialityInterface::class.java)
        return specialityInterface as SpecialityInterface
    }

    fun buildReservation(): ReservationInterface {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        reservationInterface = retrofit.create(ReservationInterface::class.java)
        return reservationInterface as ReservationInterface
    }
}