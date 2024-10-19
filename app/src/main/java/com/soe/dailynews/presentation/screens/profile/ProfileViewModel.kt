package com.soe.dailynews.presentation.screens.profile

import com.soe.dailynews.navigations.navigation.BottomBarScreenRoute
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.di.authenticationModule.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val accountService: AccountService
) : com.soe.dailynews.presentation.screens.NewsAppViewModel() {



     fun signOut(openAndPopUp: (String, String) -> Unit){
        launchCatching {
            accountService.signOut()
            openAndPopUp(ScreenRoute.SignInScreen.route, BottomBarScreenRoute.Profile.route)
        }


    }
}