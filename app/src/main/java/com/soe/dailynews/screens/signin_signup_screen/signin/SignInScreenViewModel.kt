package com.soe.dailynews.screens.signin_signup_screen.signin

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.soe.dailynews.navigation.FORGOT_PASSWORD_SCREEN
import com.soe.dailynews.navigation.HOME_SCREEN
import com.soe.dailynews.navigation.SIGN_IN_SCREEN
import com.soe.dailynews.navigation.SIGN_UP_SCREEN
import com.soe.dailynews.screens.NewsAppViewModel
import com.soe.dailynews.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : NewsAppViewModel() {


    private val _email = MutableStateFlow("")
    val email : StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password : StateFlow<String> = _password.asStateFlow()


    fun updateEmail (newEmail : String){
        _email.value = newEmail
    }

    fun updatePassword (newPassword : String){
        _password.value = newPassword
    }


    fun onClickSignIn(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            if (_email.value.isEmpty()) {
                throw IllegalArgumentException("Email cannot be empty")
            }
            if (_password.value.isEmpty()) {
                throw IllegalArgumentException("Password cannot be empty")
            }

            accountService.signInWithEmailAndPassword(_email.value, _password.value)
            openAndPopUp(HOME_SCREEN, SIGN_IN_SCREEN)
        }
    }

    fun onClickForgotPassword(navigate : (String) -> Unit) {
        launchCatching {
            accountService.forgotPassword(_email.value)
            navigate(FORGOT_PASSWORD_SCREEN)
        }
    }


    fun onClickSignUp(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            openAndPopUp(SIGN_UP_SCREEN, SIGN_IN_SCREEN)
        }

    }
}