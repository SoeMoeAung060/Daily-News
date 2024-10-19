package com.soe.dailynews.presentation.screens.splashScreen

import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.di.authenticationModule.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : com.soe.dailynews.presentation.screens.NewsAppViewModel(){


    fun onAppStart(openAndPopUp : (String, String) -> Unit){
        if(accountService.hasUser()){
            openAndPopUp(ScreenRoute.TopLevelScreenRoute.route, ScreenRoute.SplashScreen.route)
        }else{
            openAndPopUp(ScreenRoute.SignInScreen.route, ScreenRoute.SplashScreen.route)
//            onCreateAnonymousAccount(openAndPopUp)
        }

    }

//    private fun onCreateAnonymousAccount(openAndPopUp : (String, String) -> Unit){
//        launchCatching{
//            accountService.createAnonymousAccount()
//            openAndPopUp(HOME_SCREEN, SPLASH_SCREEN)
//        }
//    }
}