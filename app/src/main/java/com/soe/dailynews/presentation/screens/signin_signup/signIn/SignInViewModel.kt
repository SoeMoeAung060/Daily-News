package com.soe.dailynews.presentation.screens.signin_signup.signIn

import com.soe.dailynews.navigations.navigation.BottomBarScreenRoute
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.di.authenticationModule.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
) : com.soe.dailynews.presentation.screens.NewsAppViewModel() {


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
            openAndPopUp(BottomBarScreenRoute.Home.route, ScreenRoute.SignInScreen.route)
        }
    }

    fun onClickForgotPassword(navigate : (String) -> Unit) {
        launchCatching {
            accountService.forgotPassword(_email.value)
            navigate(ScreenRoute.ForgotPasswordScreen.route)
        }
    }


    fun onClickSignUp(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            openAndPopUp(ScreenRoute.SignUpScreen.route, ScreenRoute.SignInScreen.route)
        }

    }

    fun onAppStart(openAndPopUp : (String, String) -> Unit){
        if(accountService.hasUser()){
            openAndPopUp(ScreenRoute.TopLevelScreenRoute.route, ScreenRoute.SplashScreen.route)
        }else{
            openAndPopUp(ScreenRoute.SignInScreen.route, ScreenRoute.SplashScreen.route)
//            onCreateAnonymousAccount(openAndPopUp)
        }

    }
}