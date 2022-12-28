package vitec.sureservice.ui.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.foundation.text.ClickableText
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
import androidx.constraintlayout.compose.ConstraintLayout
import vitec.sureservice.ui.common.EventDialog

@SuppressLint("UnrememberedMutableState")

@Composable
fun LogIn(
    state: LogInState,
    onLogIn: (String, String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onDismissDialog: () -> Unit
) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val isValidate by derivedStateOf { username.isNotBlank() && password.isNotBlank() }
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            ConstraintLayout {

                Surface(
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Welcome Back!",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                        Text(
                            text = "Login to your Account",
                            style = MaterialTheme.typography.h5.copy(
                                color = MaterialTheme.colors.primary
                            )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            OutlinedTextField(
                                value = username,
                                onValueChange = { username = it },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                keyboardActions = KeyboardActions(onNext = {
                                    focusManager.moveFocus(
                                        FocusDirection.Down
                                    )
                                }),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next,
                                    keyboardType = KeyboardType.Email
                                ),
                                singleLine = true,
                                label = { Text(text = "Your username") })
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = password,
                                onValueChange = { password = it },
                                label = { Text(text = "Password") },
                                singleLine = true,
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                        onLogIn(username, password)
                                    }),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Password,
                                    imeAction = ImeAction.Done
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
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(vertical = 15.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Button(
                                    onClick = {
                                        onLogIn(username, password)
                                    },
                                    enabled= isValidate,
                                    modifier = Modifier.fillMaxWidth()
                                )
                                {
                                    Text(text = "LOGIN")
                                }

                                ClickableText(
                                    text = buildAnnotatedString {
                                        append("Do not have an Account?")

                                        withStyle(
                                            style = SpanStyle(
                                                color = MaterialTheme.colors.primary,
                                                fontWeight = FontWeight.Bold
                                            )
                                        ){
                                            append(" Sign up")
                                        }
                                    }){
                                    onNavigateToRegister()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if(state.errorMessage != null){
        EventDialog(
            errorMessage = state.errorMessage,
            onDismiss = onDismissDialog
        )
    }
}
