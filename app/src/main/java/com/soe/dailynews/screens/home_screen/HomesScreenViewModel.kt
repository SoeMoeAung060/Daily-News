package com.soe.dailynews.screens.home_screen

import com.soe.dailynews.navigation.HOME_SCREEN
import com.soe.dailynews.navigation.SIGN_IN_SCREEN
import com.soe.dailynews.screens.NewsAppViewModel
import com.soe.dailynews.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomesScreenViewModel @Inject constructor(
    private val accountService: AccountService
) : NewsAppViewModel() {


    fun onClickSignOut(openAndPopUp : (String, String) -> Unit) {
        launchCatching {
            accountService.signOut()
            openAndPopUp(SIGN_IN_SCREEN, HOME_SCREEN)
        }
    }

}