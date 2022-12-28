package vitec.sureservice.ui.common


import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(Logout: () -> Unit) {
    TopAppBar(
        actions = {
            IconButton(onClick = {
                Logout()
            }) {
                Icon(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = "Logout Icon",
                )
            }
        },
        title = { Text("") },
        backgroundColor= Color.White,
        contentColor = MaterialTheme.colors.primary,
        elevation = 0.dp
    )
}