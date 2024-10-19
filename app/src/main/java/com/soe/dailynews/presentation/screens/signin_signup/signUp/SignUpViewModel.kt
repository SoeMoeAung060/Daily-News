package com.soe.dailynews.presentation.screens.signin_signup.signUp

import com.soe.dailynews.navigations.navigation.BottomBarScreenRoute
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.di.authenticationModule.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
) : com.soe.dailynews.presentation.screens.NewsAppViewModel() {

    private val _email = MutableStateFlow("")
    val email : StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password : StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword : StateFlow<String> = _confirmPassword.asStateFlow()


    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }


    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updateConfirmPassword(newConfirmPassword: String) {
        _confirmPassword.value = newConfirmPassword
    }


    fun onSignUpClick(
        openAndPopUp: (String, String) -> Unit,
    ) {
        launchCatching {
            if (!_email.value.isValidEmail()){
                throw IllegalArgumentException("Invalid email")
            }

            if (!_password.value.isValidPassword()){
                throw IllegalArgumentException("Invalid password")
            }

            if (_password.value != confirmPassword.value) {
                throw IllegalArgumentException("Passwords do not match")
            }

            accountService.signUp(_email.value, _password.value)
            openAndPopUp(BottomBarScreenRoute.Home.route, ScreenRoute.SignUpScreen.route)
        }
    }


    fun onSignInClick(openAndPopUp: (String, String) -> Unit){
        launchCatching {
            openAndPopUp(ScreenRoute.SignInScreen.route, ScreenRoute.SignUpScreen.route)
        }

    }


}