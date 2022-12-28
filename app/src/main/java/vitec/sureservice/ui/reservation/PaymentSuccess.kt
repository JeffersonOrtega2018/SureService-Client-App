package vitec.sureservice.ui.reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.ViewModel
import vitec.sureservice.R
import vitec.sureservice.data.model.Technician
import vitec.sureservice.ui.service.ServiceViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PaymentSuccess(technicianId: Int, serviceViewModel: ServiceViewModel, technicianViewModel: TechnicianViewModel, reservation: ()-> Unit) {

    var review by remember { mutableStateOf("") }
    var calification by remember { mutableStateOf("") }

    val listItems = arrayOf("1","2","3","4","5")
    var expanded by remember { mutableStateOf(false) }
    var textFiledSize by remember {mutableStateOf(Size.Zero)}

    val technician: Technician by serviceViewModel.technician.observeAsState(Technician())

    val btnEnabled = calification.isNotEmpty()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Calification Service",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Image(
            painter = painterResource(id = R.drawable.payment_sucess),
            contentDescription = "Payment Success Image",
            modifier = Modifier
                .height(320.dp)
                .width(320.dp)
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        Text(
            text = "Rate from 1 to 5",
            style = TextStyle(color = Color.Black, fontSize = 26.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(5.dp))

        /*DropDownMenu*/
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {// text field
            OutlinedTextField(
                value = calification,
                onValueChange = {calification = it},
                readOnly = true,
                label = { Text(text = "MM") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)},
                modifier = Modifier.width(120.dp).onGloballyPositioned { coordinates -> textFiledSize = coordinates.size.toSize() },
            )
            // menu
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) { listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    calification = selectedOption
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
            }
        }
        /*DropDownMenu*/

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        OutlinedTextField(value = review, onValueChange = { review = it },
            label = { Text(text = "Review") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = TextFieldDefaults.textFieldColors(unfocusedIndicatorColor = Color.Transparent))

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(30.dp))

        Button(
            onClick = {
                technicianViewModel.editTechnician(technician, calification.toInt())
                reservation()
                      },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(colorSureService2)),
            enabled = btnEnabled
        ) {
            Text(text = "SEND",
                style = TextStyle(color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Medium))
        }


        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))
    }

}