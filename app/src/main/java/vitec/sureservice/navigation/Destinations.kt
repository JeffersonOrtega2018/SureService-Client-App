package vitec.sureservice.navigation

import androidx.navigation.NamedNavArgument

sealed class Destinations(
    val route: String,
    val arguments: List<NamedNavArgument>
){

    object Login: Destinations("login", emptyList())
    object ChangePassword: Destinations("change_password", emptyList())
    object Signup: Destinations("signup", emptyList())
    object Home: Destinations("home", emptyList())
    object Service: Destinations("service", emptyList())
    object Reservation: Destinations("reservation", emptyList())
    object Settings: Destinations("settings", emptyList())
    object TechnicianProfile: Destinations("technicianProfile/{technicianId}", emptyList()){
        fun createRoute(technicianId: Int) = "technicianProfile/$technicianId"
    }
    object BookAnAppointment: Destinations("bookAnAppointment/{technicianId}", emptyList()){
        fun createRoute(technicianId: Int) = "bookAnAppointment/$technicianId"
    }
    object DetailBookAnAppointment: Destinations("detailBookAnAppointment/{date}/{technicianName}/{technicianLastName}/{technicianDistrict}/{serviceRequestId}", emptyList()){
        fun createRoute(date: String,
                        technicianName: String,
                        technicianLastName: String,
                        technicianDistrict: String,
                        serviceRequestId: Int) = "detailBookAnAppointment/$date/$technicianName/$technicianLastName/$technicianDistrict/$serviceRequestId"
    }
    
    object SettingChangeInformation: Destinations("settings_change_information", emptyList())

    object RequestAccept: Destinations ("requestAccept/{serviceRequestId}", emptyList()) {
        fun createRoute(serviceRequestId: Int) = "requestAccept/$serviceRequestId"
    }
    object Payment: Destinations ("payment/{serviceRequestId}", emptyList()) {
        fun createRoute(serviceRequestId: Int) = "payment/$serviceRequestId"
    }
    object PaymentSuccess: Destinations ("paymentSuccess/{technicianId}", emptyList()){
        fun createRoute(technicianId: Int) = "paymentSuccess/$technicianId"
    }
    object PaymentFailed: Destinations ("paymentFailed", emptyList())

}
