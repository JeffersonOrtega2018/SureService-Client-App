package vitec.sureservice.ui.bookAnAppointment

class ServiceRequestDto(
    val detail: String,
    val total_price: Double,
    val reservation_price: Double,
    val confirmation: Int
) {
    constructor(): this("", 0.0, 0.0, 0)
}