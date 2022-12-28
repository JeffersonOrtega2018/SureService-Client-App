package vitec.sureservice.ui.reservation

class ServiceRequestPutDto(
    val total_price: Double,
    val reservation_price: Double,
    val confirmation: Int
) {
    constructor(): this(0.0, 0.0, 0)
}