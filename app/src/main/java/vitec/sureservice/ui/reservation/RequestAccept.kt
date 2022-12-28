package vitec.sureservice.ui.reservation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vitec.sureservice.R
import vitec.sureservice.data.model.ServiceRequest

var  colorSureService2 = 0xFF0332FC



@SuppressLint("UnrememberedMutableState")
@Composable
fun RequestAccept(reservationViewModel: ReservationViewModel, payment: (Int)-> Unit) {

    val serviceRequest: ServiceRequest by reservationViewModel.serviceRequest.observeAsState(
        ServiceRequest()
    )
    val day = (1..28).random()
    val date = "$day-11-2022"

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp)
        .verticalScroll(rememberScrollState())) {

        Text(
            text = "${serviceRequest.technician.name} ${serviceRequest.technician.last_name}",
            style = TextStyle(color = Color.Black, fontSize = 34.sp, fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))


        Row() {
            Text(
                text = "Selected Date: ",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = date,
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Medium)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))


        Row() {
            Text(
                text = "Location: ",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = "${serviceRequest.technician.district}",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Medium)
            )
        }


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))

        Column() {
            Text(
                text = "Detail: ",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = "${serviceRequest.detail}",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Medium)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))

        Row() {
            Text(
                text = "Reservation Price: ",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = "${serviceRequest.reservation_price} soles",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Medium)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))

        Row() {
            Text(
                text = "Total Price: ",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            )

            Text(
                text = "${serviceRequest.total_price} soles",
                style = TextStyle(color = Color.Black, fontSize = 22.sp, fontWeight = FontWeight.Medium)
            )
        }


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.request_acepted),
                contentDescription = "Request Accept Image",
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
            )

            Text(
                text = "Ask the technician this ID when approaching your home",
                style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium),
                textAlign = TextAlign.Center
            )

            Text(
                text = "${serviceRequest.id}",
                style = TextStyle(color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Bold)
            )
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Button(
            onClick = { payment(serviceRequest.id) },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorSureService2)),
        ) {
            Text(text = "PAY",
                style = TextStyle(color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium))
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))


    }
}