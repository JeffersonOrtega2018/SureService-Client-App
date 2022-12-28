package vitec.sureservice.ui.reservation

import android.app.Service
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import vitec.sureservice.data.model.ServiceRequest
import vitec.sureservice.data.model.Technician
import vitec.sureservice.ui.bookAnAppointment.ServiceRequestViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Payment(serviceRequestId: Int, reservationViewModel: ReservationViewModel, serviceRequestViewModel: ServiceRequestViewModel, reservation: ()-> Unit) {

    var cardNumber by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var securityCode by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    val listItems = arrayOf("01","02","03","04","05","06","07","08","09","10","11","12")
    var expanded by remember { mutableStateOf(false) }
    var textFiledSize by remember {mutableStateOf(Size.Zero)}

    val serviceRequest: ServiceRequest by reservationViewModel.serviceRequest.observeAsState(ServiceRequest())

    val btnEnabled = cardNumber.isNotEmpty() && month.isNotEmpty() && year.isNotEmpty() && securityCode.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty()


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp)
        .verticalScroll(rememberScrollState()))  {

        Text(
            text = "Add Credit/Debid Card",
            style = TextStyle(color = Color.Black, fontSize = 38.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        OutlinedTextField(
            value = cardNumber,
            onValueChange = { if (it.length < 17) cardNumber = it },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            label = { Text(text = "Card Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))


        Row() {

            Column(
                modifier = Modifier
                    .height(70.dp), verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Expirity",
                    style = TextStyle(color = Color.Black, fontSize = 30.sp, fontWeight = FontWeight.Bold),
                )
            }

            Spacer(modifier = Modifier
                .width(15.dp))


            /*DropDownMenu*/
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {// text field
                OutlinedTextField(
                    value = month,
                    onValueChange = {month = it},
                    readOnly = true,
                    label = { Text(text = "MM") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                    modifier = Modifier
                        .width(115.dp)
                        .onGloballyPositioned { coordinates ->
                            textFiledSize = coordinates.size.toSize()
                        },
                )
                // menu
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) { listItems.forEach { selectedOption ->
                        // menu item
                        DropdownMenuItem(onClick = {
                            month = selectedOption
                            expanded = false
                        }) {
                            Text(text = selectedOption)
                        }
                    }
                }
            }
            /*DropDownMenu*/


            Spacer(modifier = Modifier
                .width(15.dp))

            OutlinedTextField(
                value = year,
                onValueChange = { if (it.length < 3) year = it },
                modifier = Modifier
                    .width(115.dp),
                singleLine = true,
                label = { Text(text = "YY") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))



        OutlinedTextField(
            value = securityCode,
            onValueChange = { if (it.length < 4) securityCode = it },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            label = { Text(text = "Security Code") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))


        OutlinedTextField(value = firstName, onValueChange = {firstName = it},
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            label = {Text(text = "First Name")}
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))


        OutlinedTextField(value = lastName, onValueChange = {lastName = it},
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            label = {Text(text = "Last Name")}
        )


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Button(
            onClick = {
                reservationViewModel.postReservation(serviceRequestId)
                serviceRequestViewModel.putServiceRequestById(3, serviceRequest)
                reservation() /* Payment Route */
                      },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorSureService2)),
            enabled = btnEnabled
        ) {
            Text(text = "PAY",
                style = TextStyle(color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium))
        }

    }


}
