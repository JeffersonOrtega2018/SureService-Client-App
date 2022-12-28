package vitec.sureservice.ui.service

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import vitec.sureservice.data.model.Speciality
import vitec.sureservice.data.model.Technician
import vitec.sureservice.ui.theme.BlueColor
import vitec.sureservice.ui.theme.LilaColor

var specialitySelected by mutableStateOf(0)

@Composable
fun Service(
    serviceViewModel: ServiceViewModel,
    specialityViewModel: SpecialityViewModel,
    goToTechnicianProfile: (Int) -> Unit
){
    var place by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var enabled = specialitySelected != 0

    val specialities: List<Speciality> by specialityViewModel.specialities.observeAsState(listOf())
    val technicians: List<Technician> by serviceViewModel.technicians.observeAsState(listOf())

    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spinner(specialities)

        OutlinedTextField(value = place, onValueChange = {place = it},
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            label = {Text(text = "Location")}
        )
        OutlinedTextField(value = rating, onValueChange = {rating = it},
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            label = {Text(text = "Rating (0-5)")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(
            onClick = { serviceViewModel.getTechniciansBySpeciality(specialitySelected, place, rating) },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text(text = "SEARCH")
        }

        Spacer(modifier = Modifier.height(4.dp))
        
        LazyColumn {
            items(technicians) { technician ->
                TechnicianCard(technician, goToTechnicianProfile)
            }
        }
    }
}

@Composable
fun Spinner (items: List<Speciality>) {
    var specialityText by remember { mutableStateOf("What are you looking for?") }
    var expanded by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxWidth()) {
        Row(Modifier
            .fillMaxWidth()
            .clickable {
                expanded = !expanded
            }
            .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            Row(Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = specialityText, fontSize = 16.sp, modifier = Modifier.padding(end = 8.dp))
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            }


            DropdownMenu(
                modifier = Modifier.fillMaxWidth(),
                expanded = expanded,
                onDismissRequest = { expanded = false })
            {
                items.forEach {
                        item -> DropdownMenuItem(onClick = {
                    expanded = false
                    specialityText = item.name
                    specialitySelected = item.id
                }) {
                    Text(text = item.name)
                }
                }
            }
        }
    }
}

@Composable
fun TechnicianCard(
    technician: Technician,
    goToTechnicianProfile: (Int) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 3.dp
    ) {
        Column (modifier = Modifier.padding(16.dp),  verticalArrangement = Arrangement.spacedBy(25.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                ) {
                AsyncImage(
                    model = technician.image_url,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = "${technician.name} ${technician.last_name}",
                        fontSize = 20.sp, fontWeight = FontWeight.Medium
                    )
                    Valoration(technician.valoration)
                }
            }
            Column (verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(text = "Profession: ${technician.speciality.name}", fontSize = 14.sp)
                Text(text = "Location: ${technician.district}", fontSize = 14.sp)
            }
            TextButton(onClick = { goToTechnicianProfile(technician.id) }) {
                Text(text = "MORE INFO", fontWeight = FontWeight.Bold, color = BlueColor)
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun Valoration(valoration: Int) {
    Row(modifier = Modifier.padding(top = 5.dp)) {
        for (i in 1..5) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = if (i <= valoration) BlueColor else LilaColor,
                modifier = Modifier.size(10.dp)
            )
        }
    }
}