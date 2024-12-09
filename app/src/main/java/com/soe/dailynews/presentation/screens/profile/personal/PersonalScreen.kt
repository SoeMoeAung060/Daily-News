//package com.soe.dailynews.presentation.screens.profile.personal
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.soe.dailynews.R
//import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
//import com.soe.dailynews.presentation.ui.theme.Dimensions.mainButtonHeight
//import com.soe.dailynews.presentation.ui.theme.Dimensions.mainButtonWidth
//import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingNormal
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PersonalScreen(
//    modifier: Modifier = Modifier,
//    popUp: () -> Unit,
//    viewModel: PersonalViewModel = hiltViewModel(),
//) {
//
//    val userData = viewModel.userData.collectAsState()
//
//    val nameState = remember { mutableStateOf(userData.value.name) }
//    val emailState = remember { mutableStateOf(userData.value.email) }
//    val dateOfBirthState = remember { mutableStateOf(userData.value.dateOfBirth) }
//    val passwordState = remember { mutableStateOf(userData.value.password) }
//
//
//    var isPasswordVisible by remember { mutableStateOf(false) }
//
//
//    Column(modifier = modifier.fillMaxSize()) {
//        TopAppBar(
//            title = {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Start,
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    IconButton(
//                        onClick = popUp
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.back),
//                            contentDescription = "back"
//                        )
//                    }
//                    Text("Personal")
//                }
//
//            },
//            colors = TopAppBarDefaults.topAppBarColors(
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                actionIconContentColor = MaterialTheme.colorScheme.tertiary,
//                titleContentColor = MaterialTheme.colorScheme.tertiary
//            ),
//        )
//
//        Column(
//            modifier = Modifier.padding(paddingNormal)
//        ) {
//            OutlinedTextField(
//                value = nameState.value,
//                onValueChange = { nameState.value = it },
//                label = { Text(text = "Your Name") },
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Transparent, RoundedCornerShape(8.dp))
//            )
//
//            OutlinedTextField(
//                value = emailState.value,
//                onValueChange = { emailState.value = it },
//                label = { Text(text = "Email") },
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Transparent, RoundedCornerShape(8.dp))
//            )
//
//            OutlinedTextField(
//                value = dateOfBirthState.value,
//                onValueChange = { dateOfBirthState.value = it },
//                label = { Text(text = "Date Of Birth") },
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Transparent, RoundedCornerShape(8.dp))
//            )
//
//            OutlinedTextField(
//                value = passwordState.value,
//                onValueChange = { passwordState.value = it },
//                label = { Text(text = "Password") },
//                singleLine = true,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.Transparent, RoundedCornerShape(8.dp)),
//                trailingIcon = {
//                    IconButton(
//                        onClick = {
//                            isPasswordVisible = !isPasswordVisible
//                        }
//                    ) {
//                        Icon(
//                            painter = if (isPasswordVisible) painterResource(R.drawable.visibility) else painterResource(
//                                R.drawable.visibility_off
//                            ),
//                            contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
//                        )
//                    }
//                },
//                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
//            )
//
//        }
//
//        Button(
//            onClick = {
//                viewModel.saveUserData(
//                    name = nameState.value,
//                    email = emailState.value,
//                    dateOfBirth = dateOfBirthState.value,
//                    password = passwordState.value
//                )
//            },
//            modifier = Modifier
//                .width(mainButtonWidth)
//                .height(mainButtonHeight)
//                .align(Alignment.CenterHorizontally),
//            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
//
//            ) {
//            Text(text = "Save")
//        }
//    }
//
//
//}
//
//
//@Preview(showBackground = true)
//@Composable
//private fun PersonalScreenPreview() {
//
//    DailyNewsTheme {
//
//        PersonalScreen(
//            popUp = {}
//        )
//    }
//
//}