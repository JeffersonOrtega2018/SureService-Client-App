package vitec.sureservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import vitec.sureservice.navigation.Destinations
import vitec.sureservice.navigation.Navigation
import vitec.sureservice.ui.theme.SureServiceTheme

@AndroidEntryPoint
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SureServiceTheme {
                val navController = rememberAnimatedNavController()

                Navigation(navController, Destinations.Login.route, navController)
            }
        }
    }
}

