package com.soe.dailynews.screens.signin_signup_screen.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
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
fun SignInScreen(
    modifier: Modifier = Modifier,
    openAndPopUp: (String, String) -> Unit,
    onForgotPasswordClick: (String) -> Unit,
) {

    Scaffold { innerPadding ->
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
                SignInForm(
                    openAndPopUp = openAndPopUp,
                    onForgotPasswordClick = onForgotPasswordClick
                )
            }
        }
    }
}

@Composable
fun SignInForm(
    openAndPopUp: (String, String) -> Unit,
    onForgotPasswordClick: (String) -> Unit,
    viewModel: SignInScreenViewModel = hiltViewModel()
) {


    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()


    var isPasswordVisible by remember { mutableStateOf(false) }


    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "splash_screen"
    )

    Spacer(modifier = Modifier.height(40.dp))


    OutlinedTextField(
        value = email.value,
        onValueChange = { viewModel.updateEmail(it) },
        label = { Text("Email") },
        placeholder = { Text("Enter your email") },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.mail),
                contentDescription = "splash_screen"
            )
        }
    )

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = password.value,
        onValueChange = { viewModel.updatePassword(it) },
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

    Spacer(modifier = Modifier.height(35.dp))

    Button(
        onClick = {
            viewModel.onClickSignIn(
                openAndPopUp
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text("Sign In")
    }

    Spacer(modifier = Modifier.height(5.dp))


    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        TextButton(
            onClick = { viewModel.onClickForgotPassword(onForgotPasswordClick) }
        ) {
            Text(
                text = "Forgot Password?",
                fontSize = 16.sp
            )
        }
    }

    Spacer(modifier = Modifier.height(30.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .padding(end = 20.dp)
                .background(MaterialTheme.colorScheme.onBackground)
        )

        Text(
            text = "or sign in with"
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .padding(start = 20.dp)
                .background(MaterialTheme.colorScheme.onBackground)
        )

    }

    Spacer(modifier = Modifier.height(30.dp))


//    SignInWithAuthentication()


    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = { viewModel.onClickSignUp(openAndPopUp) }
        ) {
            Text(
                text = "Don't have and account? Sign Up!",
                fontSize = 16.sp
            )
        }
    }



}


@Composable
fun SignInWithAuthentication(
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AuthenticationButton(
            modifier = Modifier.size(60.dp),
            onClick = {},
            icon = painterResource(R.drawable.google)
        )

        Spacer(modifier = Modifier.width(20.dp))

        AuthenticationButton(
            modifier = Modifier.size(60.dp),
            onClick = {},
            icon = painterResource(R.drawable.facebook)
        )


    }
}


@Composable
fun AuthenticationButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Painter
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .clip(RoundedCornerShape(50.dp)) // To ensure that the corners are rounded, you should apply the clip modifier after applying the background
            .background(color = MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = icon,
            contentDescription = "google",
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.Center),
            tint = Color.White

        )
    }

}


@Preview
@Composable
private fun SignInScreenPreview() {
    DailyNewsTheme {
        SignInScreen(
            openAndPopUp = { _, _ -> },
            onForgotPasswordClick = {}

        )
    }
}

