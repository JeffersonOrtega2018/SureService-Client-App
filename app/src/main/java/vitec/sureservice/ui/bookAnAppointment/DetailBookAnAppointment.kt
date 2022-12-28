package vitec.sureservice.ui.bookAnAppointment

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vitec.sureservice.R


@Composable
fun DetailBookAnAppointment(
    date: String,
    technicianName: String,
    technicianLastName: String,
    technicianDistrict: String,
    serviceRequestId: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$technicianName $technicianLastName",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 25.sp)) {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Select Date: ")
                    }
                    append(date)
                    append("\n")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Location: ")
                    }
                    append("$technicianDistrict")
                }
            },
            style = TextStyle(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.request_sent),
                contentDescription = "Sent Image",
                modifier = Modifier
                    .height(240.dp)
                    .width(300.dp),
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "ID: $serviceRequestId",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Medium)
        )
    }
}