package vitec.sureservice.ui.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import vitec.sureservice.ui.common.EventDialog

@SuppressLint("UnrememberedMutableState")
@Composable
fun SignUp(
    state: SignUpState,
    onSignUp: (String, String, String, String, String,String, String, String) -> Unit,
    onLogIn: () -> Unit,
    onDismissDialog: () -> Unit
) {
    var email by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }
    var username by rememberSaveable { mutableStateOf(value = "") }
    var name by rememberSaveable { mutableStateOf(value = "") }
    var lastName by rememberSaveable { mutableStateOf(value = "") }
    var telephoneNumber by rememberSaveable { mutableStateOf(value = "") }
    var dni by rememberSaveable { mutableStateOf(value = "") }
    var confirmPassword by rememberSaveable { mutableStateOf(value = "") }

    val isValidate by derivedStateOf { email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty() && name.isNotEmpty() && lastName.isNotEmpty() && telephoneNumber.isNotEmpty() && dni.isNotEmpty() }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Create An Account",
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.primary
                )
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
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

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Password Toggle"
                            )

                        }
                    }
                )

                OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text(text = "Confirm Password") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()

                                onSignUp(
                                    email,
                                    password,
                                    username,
                                    name,
                                    lastName,
                                    telephoneNumber,
                                    dni,
                                    confirmPassword
                                )
                            }
                        ),
                        visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }) {
                                Icon(
                                    imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = "Password Toggle"
                                )

                            }
                        }
                    )

                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = {
                            onSignUp(
                                email,
                                password,
                                username,
                                name,
                                lastName,
                                telephoneNumber,
                                dni,
                                confirmPassword
                            )},
                        enabled= isValidate,
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        Text(text = "Sign Up")
                    }

                    ClickableText(
                        text = buildAnnotatedString {
                            append("Already have an account?")

                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            ){
                                append(" Log in")
                            }
                        }){
                        onLogIn()
                    }

            }
        }
    }
  }

    if(state.errorMessage != null) {
        EventDialog(errorMessage = state.errorMessage, onDismiss = onDismissDialog)
    }
}