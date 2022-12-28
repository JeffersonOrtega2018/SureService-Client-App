package vitec.sureservice.ui.settings

data class SettingsDto (
    var username: String,
    var email: String,
    var name: String,
    var last_name: String,
    var telephone_number: String,
    var dni: String,
    val amount_reservation: Int
)