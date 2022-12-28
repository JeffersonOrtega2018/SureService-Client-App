package vitec.sureservice.ui.signup

data class SignupDto (
    val username: String,
    val email: String,
    val password: String,
    val name: String,
    val last_name: String,
    val telephone_number: String,
    val dni: String,
)