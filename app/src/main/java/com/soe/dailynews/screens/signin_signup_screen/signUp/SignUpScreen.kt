package com.soe.dailynews.screens.signin_signup_screen.signUp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soe.dailynews.R
import com.soe.dailynews.ui.theme.DailyNewsTheme

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    openAndPopUp: (String, String) -> Unit,
) {


    Scaffold{ innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 54.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SignUpForm(
                    openAndPopUp = openAndPopUp
                )
            }

        }
    }
    
}


@Composable
fun SignUpForm(
    openAndPopUp: (String, String) -> Unit,
    viewModel: SignUpScreenViewModel = hiltViewModel()

) {

    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val confirmPassword = viewModel.confirmPassword.collectAsState()

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "splash_screen"
    )

    Spacer(modifier = Modifier.height(40.dp))


    OutlinedTextField(
        value = email.value,
        onValueChange = {viewModel.updateEmail(it)},
        label = { Text("Email") },
        placeholder = { Text("Enter your email") },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.mail),
                contentDescription = "splash_screen"
            )
        },


        )

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = password.value,
        onValueChange = {viewModel.updatePassword(it)},
        label = { Text("Password") },
        placeholder = { Text("Enter your password") },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.lock),
                contentDescription = "lock"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                }
            ) {
                Icon(
                    painter = if (isPasswordVisible) painterResource(R.drawable.visibility) else painterResource(
                        R.drawable.visibility_off
                    ),
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        }

    )

    Spacer(modifier = Modifier.height(20.dp))


    OutlinedTextField(
        value = confirmPassword.value,
        onValueChange = {viewModel.updateConfirmPassword(it)},
        label = { Text("Confirm Password") },
        placeholder = { Text("Enter your password") },
        singleLine = true,
        visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.lock),
                contentDescription = "lock"
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    isPasswordVisible = !isPasswordVisible
                }
            ) {
                Icon(
                    painter = if (isPasswordVisible) painterResource(R.drawable.visibility) else painterResource(
                        R.drawable.visibility_off
                    ),
                    contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                )
            }
        }

    )

    Spacer(modifier = Modifier.height(35.dp))

    Button(
        onClick = {viewModel.onSignUpClick(openAndPopUp)},
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Sign Up")
    }

    Spacer(modifier = Modifier.height(5.dp))


    Spacer(modifier = Modifier.height(30.dp))



    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        TextButton(
            onClick = { viewModel.onSignInClick(openAndPopUp) }
        ) {
            Text(
                text = "Already have an account? Sign In",
                fontSize = 16.sp
            )
        }
    }

}


@Preview
@Composable
private fun SignInScreenPreview() {
    DailyNewsTheme {
        SignUpScreen(
            openAndPopUp = {_, _ ->}
        )
    }

}