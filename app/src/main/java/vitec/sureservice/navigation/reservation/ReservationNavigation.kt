package vitec.sureservice.navigation.reservation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import vitec.sureservice.navigation.Destinations
import vitec.sureservice.ui.bookAnAppointment.ServiceRequestViewModel
import vitec.sureservice.ui.reservation.*
import vitec.sureservice.ui.service.ServiceViewModel


@ExperimentalAnimationApi
@Composable
fun ReservationNavigation(navControllerFather: NavHostController, navController: NavHostController) {

    BoxWithConstraints {
        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.Reservation.route
        ){
            addReservation(navController, navControllerFather)
            addRequestAccept(navController)
            addPayment(navController)
            addPaymentSuccess(navController)
            addPaymentFailed(navController)

        }
    }
}


@ExperimentalAnimationApi
fun NavGraphBuilder.addReservation(
    navController: NavHostController,
    navControllerFather: NavHostController
){
    composable(
        route = Destinations.Reservation.route
    ){
        val reservationViewModel: ReservationViewModel = hiltViewModel()
        reservationViewModel.getServiceRequestsByClientId()

        Reservation(
            reservationViewModel,
            { navController.navigate(Destinations.RequestAccept.createRoute(it))},
            { navController.navigate(Destinations.PaymentSuccess.createRoute(it)) } )

    }
}


@ExperimentalAnimationApi
fun NavGraphBuilder.addRequestAccept(navController: NavHostController){
    composable(
        route = Destinations.RequestAccept.route
    ){ navBackStackEntry ->
        val serviceRequestId = navBackStackEntry.arguments?.getString("serviceRequestId")!!
        val reservationViewModel: ReservationViewModel = hiltViewModel()
        reservationViewModel.getServiceRequestById(serviceRequestId.toInt())
        RequestAccept(reservationViewModel) {
            navController.navigate(Destinations.Payment.createRoute(it))
        }
    }
}


@ExperimentalAnimationApi
fun NavGraphBuilder.addPayment(navController: NavHostController){
    composable(
        route = Destinations.Payment.route
    ){ navBackStackEntry ->
        val serviceRequestId = navBackStackEntry.arguments?.getString("serviceRequestId")!!
        val reservationViewModel: ReservationViewModel = hiltViewModel()
        reservationViewModel.getServiceRequestById(serviceRequestId.toInt())
        val serviceRequestViewModel: ServiceRequestViewModel = hiltViewModel()
        Payment(serviceRequestId.toInt(), reservationViewModel, serviceRequestViewModel) {
            navController.navigate(Destinations.Reservation.route)
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addPaymentSuccess(navController: NavHostController){
    composable(
        route = Destinations.PaymentSuccess.route
    ){
            navBackStackEntry ->
        val technicianId = navBackStackEntry.arguments?.getString("technicianId")!!
        val serviceViewModel: ServiceViewModel = hiltViewModel()
        serviceViewModel.getATechnicianById(technicianId.toInt())
        val technicianViewModel: TechnicianViewModel = hiltViewModel()

        PaymentSuccess(technicianId.toInt(), serviceViewModel, technicianViewModel) {
            navController.navigate(Destinations.Reservation.route)
        }
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.addPaymentFailed(navController: NavHostController){
    composable(
        route = Destinations.PaymentFailed.route
    ){
        PaymentFailed() {
            navController.navigate(Destinations.Reservation.route)
        }
    }


}