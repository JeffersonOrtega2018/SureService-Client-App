package vitec.sureservice.ui

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import vitec.sureservice.navigation.Destinations
import vitec.sureservice.navigation.Navigation

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalAnimationApi
@Composable
fun MainScreen(navControllerFather: NavHostController) {
    val navController = rememberAnimatedNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Navigation(navControllerFather, Destinations.Service.route, navController)
    }

}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        NavigationScreen.Service,
        NavigationScreen.Reservation,
        NavigationScreen.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavigationScreen,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = { Icon(
            imageVector = screen.icon,
            contentDescription = "Navigation Icon"
        ) },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled) ,
        onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
        }
    )

}

sealed class NavigationScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Service: NavigationScreen(
        route = "service",
        title = "Service",
        icon = Icons.Default.EditCalendar
    )

    object Reservation: NavigationScreen(
        route = "reservation",
        title = "Reservation",
        icon = Icons.Default.Approval
    )

    object Settings: NavigationScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}