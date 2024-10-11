package com.soe.dailynews.screens.splash_screen

import com.soe.dailynews.navigation.HOME_SCREEN
import com.soe.dailynews.navigation.SIGN_IN_SCREEN
import com.soe.dailynews.navigation.SPLASH_SCREEN
import com.soe.dailynews.screens.NewsAppViewModel
import com.soe.dailynews.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : NewsAppViewModel(){

    fun onAppStart(openAndPopUp : (String, String) -> Unit){
        if(accountService.hasUser()){
            openAndPopUp(HOME_SCREEN, SPLASH_SCREEN)
        }else{
            openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
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