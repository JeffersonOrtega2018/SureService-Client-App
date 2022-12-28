package vitec.sureservice.navigation.setting

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import vitec.sureservice.navigation.Destinations
import vitec.sureservice.ui.login.LogInViewModel
import vitec.sureservice.ui.settings.SettingChangeInformation
import vitec.sureservice.ui.settings.SettingChangePassword
import vitec.sureservice.ui.settings.SettingViewModel
import vitec.sureservice.ui.settings.Settings

@ExperimentalAnimationApi
@Composable
fun SettingNavigation(navControllerFather: NavHostController, navController: NavHostController) {

    BoxWithConstraints {
        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.Settings.route
        ){
            addSettings(navController, navControllerFather)
            addSettingChangeInformation(navController)
            addChangePassword(navController)
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addSettings(
    navController: NavHostController,
    navControllerFather: NavHostController
){
    composable(
        route = Destinations.Settings.route
    ){
        val loginViewModel: LogInViewModel = hiltViewModel()
        val viewModel: SettingViewModel = hiltViewModel()

        Settings(viewModel.client,
            {
                loginViewModel.clientDao.deleteClient(loginViewModel.client)
                navControllerFather.navigate(Destinations.Login.route){
                    popUpTo(Destinations.Home.route){
                        inclusive = true
                    }
                }
            },
            {
                navController.navigate(Destinations.SettingChangeInformation.route)
            })
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addSettingChangeInformation(navController: NavHostController){
    composable(
        route = Destinations.SettingChangeInformation.route,
    ){
        val settingViewModel: SettingViewModel = hiltViewModel()
        SettingChangeInformation(
            settingViewModel.client,
            settingViewModel.state.value,
            settingViewModel::updateClient,
            settingViewModel::hideErrorDialog,
            navController
        )
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addChangePassword(navController: NavHostController){
    composable(
        route = Destinations.ChangePassword.route,
    ){
        val settingViewModel: SettingViewModel = hiltViewModel()
        SettingChangePassword(
            settingViewModel.client,
            settingViewModel.state.value,
            settingViewModel::updatePassword,
            settingViewModel::hideErrorDialog,
            navController
        )
    }
}