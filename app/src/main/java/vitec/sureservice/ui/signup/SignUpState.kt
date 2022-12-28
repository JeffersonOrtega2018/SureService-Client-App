package vitec.sureservice.ui.signup

import androidx.annotation.StringRes

data class SignUpState(
    val successSignUp: Boolean = false,
    @StringRes val errorMessage: Int ?= null
)