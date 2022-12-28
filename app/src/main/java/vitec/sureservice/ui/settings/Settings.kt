package vitec.sureservice.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import vitec.sureservice.R
import vitec.sureservice.data.model.Client

@Composable
fun Settings(client: Client,Logout: ()-> Unit, ChangeInformation: (Long)-> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        /*AsyncImage(
            model = client.image_url,
            contentDescription = "profile image"
        )*/
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "${client.name} ${client.last_name}",
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.h4
        )
        Row(modifier = Modifier.padding(vertical = 5.dp)) {
            Text(text = "Username: ", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
            Text(text = client.username, fontSize = 17.sp)
        }
        Row(modifier = Modifier.padding(vertical = 5.dp)) {
            Text(text = "Telephone Number: ", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
            Text(text = client.telephone_number, fontSize = 17.sp)
        }
        Row(modifier = Modifier.padding(vertical = 5.dp)) {
            Text(text = "DNI: ", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
            Text(text =  client.dni, fontSize = 17.sp)
        }
        Row(modifier = Modifier.padding(vertical = 5.dp)) {
            Text(text = "Email: ", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
            Text(text = client.email, fontSize = 17.sp)

        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { ChangeInformation(client.id) })
        {
            Text(text = "Edit Profile")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { Logout() })
        {
            Text(text = "Logout")
        }
    }
}
