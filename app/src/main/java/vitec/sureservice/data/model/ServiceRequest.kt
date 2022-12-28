package vitec.sureservice.data.model

class ServiceRequest (
    val id: Int,
    val detail: String,
    val total_price: Double,
    val reservation_price: Double,
    val confirmation: Int,
    val technician: Technician,
    ) {
    constructor(): this(0, "", 0.0, 0.0, 0, technician = Technician())

}