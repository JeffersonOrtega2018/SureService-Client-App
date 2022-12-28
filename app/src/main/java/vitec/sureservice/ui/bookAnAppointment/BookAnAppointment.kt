package vitec.sureservice.ui.bookAnAppointment

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import vitec.sureservice.data.model.ServiceRequest
import vitec.sureservice.data.model.Technician
import vitec.sureservice.navigation.Destinations
import vitec.sureservice.ui.service.ServiceViewModel
import vitec.sureservice.ui.theme.BlueColor
import vitec.sureservice.ui.theme.LilaColor
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun BookAnAppointment(
    technicianId: Int,
    serviceViewModel: ServiceViewModel,
    serviceRequestViewModel: ServiceRequestViewModel,
    goToDetailBookAnAppointment: NavHostController
) {

    serviceViewModel.getATechnicianById(technicianId)
    val technician: Technician by serviceViewModel.technician.observeAsState(Technician())

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${technician.name} ${technician.last_name} Confirmation",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        ChooseDate(technician, goToDetailBookAnAppointment, serviceRequestViewModel)
    }
}

@Composable
fun ChooseDate(technician: Technician, goToDetailBookAnAppointment: NavHostController, serviceRequestViewModel: ServiceRequestViewModel){
    val mContext = LocalContext.current

    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mYear-${mMonth+1}-$mDayOfMonth"
        }, mYear, mMonth, mDay,
    )

    Column(
        modifier = Modifier.padding(vertical = 15.dp)
    )
    {
        Button(
            onClick = { mDatePickerDialog.show() },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = LilaColor)
        ){
            Text(text = "DATE PICKER", color = Color.White)
        }

        Spacer(modifier = Modifier.height(15.dp))

        AppointmentDetails(mDate.value, technician, goToDetailBookAnAppointment, serviceRequestViewModel)
    }
}

@Composable
fun AppointmentDetails(date: String, technician: Technician, goToDetailBookAnAppointment: NavHostController, serviceRequestViewModel: ServiceRequestViewModel) {

    var reason by remember { mutableStateOf("") }
    val btnEnabled = date.isNotEmpty() && reason.isNotEmpty()

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
                append("${technician.district}")
            }
        },
        style = TextStyle(fontSize = 18.sp)
    )

    OutlinedTextField(
        value = reason,
        onValueChange = { reason = it },
        label = { Text("Description of the visit") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 75.dp)
            .height(150.dp)
    )

    val serviceRequest: ServiceRequest by serviceRequestViewModel.serviceRequest.observeAsState(
        ServiceRequest()
    )

    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val currentDate = sdf.format(Date())

    Button(
        onClick = {
            val fd: Date = sdf.parse(date)
            val sd: Date = sdf.parse(currentDate)
            if (fd.compareTo(sd) > 0) {
                serviceRequestViewModel.createServiceRequestDto(reason)
                serviceRequestViewModel.postServiceRequest(technician.id)

                goToDetailBookAnAppointment.navigate(
                    Destinations.DetailBookAnAppointment.createRoute(date, technician.name, technician.last_name, technician.district, serviceRequest.id)
                )
            }
                  },
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = BlueColor),
        enabled = btnEnabled
    )
    {
        Text(text = "REQUEST SERVICE", color = Color.White)
    }
}