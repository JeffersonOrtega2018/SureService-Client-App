package vitec.sureservice.ui.settings

import androidx.annotation.StringRes

data class SettingState (
    val successSignUp: Boolean = false,
    @StringRes val errorMessage: Int ?= null
)