package vitec.sureservice.ui.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
fun PaymentFailed(reservation: ()-> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Ups, an error ocurred",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))


        Image(
            painter = painterResource(id = R.drawable.error_ocurred),
            contentDescription = "Payment Success Image",
            modifier = Modifier
                .height(320.dp)
                .width(320.dp)
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))


        Text(
            text = "Error 03233",
            style = TextStyle(color = Color.Black, fontSize = 35.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))


        Text(
            text = "Probably you have online shopping disable",
            style = TextStyle(color = Color.Black, fontSize = 26.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))


        Button(
            onClick = { reservation() /* Payment Failed Route */},
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorSureService2)),
        ) {
            Text(text = "TRY AGAIN",
                style = TextStyle(color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium))
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))

    }


}