package com.soe.dailynews.screens.signin_signup_screen.signUp

import com.soe.dailynews.navigation.HOME_SCREEN
import com.soe.dailynews.navigation.SIGN_IN_SCREEN
import com.soe.dailynews.navigation.SIGN_UP_SCREEN
import com.soe.dailynews.screens.NewsAppViewModel
import com.soe.dailynews.service.AccountService
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : NewsAppViewModel() {

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
            openAndPopUp(HOME_SCREEN, SIGN_UP_SCREEN)
        }
    }


    fun onSignInClick(openAndPopUp: (String, String) -> Unit){
        launchCatching {
            openAndPopUp(SIGN_IN_SCREEN, SIGN_UP_SCREEN)
        }

    }


}