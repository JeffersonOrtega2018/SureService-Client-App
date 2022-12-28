package vitec.sureservice.ui.settings

data class UpdatePasswordDto (
    var newPassword: String,
    var confirmPassword: String,
)