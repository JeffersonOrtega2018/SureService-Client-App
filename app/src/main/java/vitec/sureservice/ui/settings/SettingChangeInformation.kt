package vitec.sureservice.ui.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import vitec.sureservice.data.model.Client
import vitec.sureservice.navigation.Destinations
import vitec.sureservice.ui.common.EventDialog

@SuppressLint("UnrememberedMutableState")
@Composable
fun SettingChangeInformation(
    client: Client,
    state: SettingState,
    updateClient: (Client, String, String, String, String, String, String, NavHostController) -> Unit,
    onDismissDialog: () -> Unit,
    navController: NavHostController
) {
    var email by rememberSaveable { mutableStateOf(value = "") }
    var username by rememberSaveable { mutableStateOf(value = "") }
    var name by rememberSaveable { mutableStateOf(value = "") }
    var lastName by rememberSaveable { mutableStateOf(value = "") }
    var telephoneNumber by rememberSaveable { mutableStateOf(value = "") }
    var dni by rememberSaveable { mutableStateOf(value = "") }


    val _email = client.email
    val _username = client.username
    val _name = client.name
    val _lastName = client.last_name
    val _telephoneNumber = client.telephone_number
    val _dni = client.dni



    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    placeholder = {Text(text = _name)},
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = { Text(text = "Name") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )

                OutlinedTextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    placeholder = {Text(text = _lastName)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    label = { Text(text = "Last name") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )

                OutlinedTextField(
                    value = telephoneNumber,
                    onValueChange = { telephoneNumber = it },
                    placeholder = {Text(text = _telephoneNumber)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    label = { Text(text = "Telephone Number") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )

                OutlinedTextField(
                    value = dni,
                    onValueChange = { dni = it },
                    placeholder = {Text(text = _dni)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    label = { Text(text = "ID number") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    placeholder = {Text(text = _username)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    label = { Text(text = "Username") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    placeholder = {Text(text = _email)},
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    label = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = {
                                updateClient(
                                    client,
                                    email,
                                    username,
                                    name,
                                    lastName,
                                    telephoneNumber,
                                    dni,
                                    navController
                                )
                              },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 1.dp)
                    )
                    {
                        Text(text = "Update Information")
                    }

                    Button(
                        onClick = {
                            navController.navigate(Destinations.ChangePassword.route)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    {
                        Text(text = "Change Password")
                    }

                }
            }
        }
    }

    if(state.errorMessage != null) {
        EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
    }
}