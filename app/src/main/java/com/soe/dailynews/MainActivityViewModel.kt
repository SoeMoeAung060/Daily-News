//package com.soe.dailynews
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.soe.dailynews.di.authenticationModule.AccountService
//import com.soe.dailynews.navigations.navigation.ScreenRoute
//import dagger.hilt.android.lifecycle.HiltViewModel
//import javax.inject.Inject
//
//@HiltViewModel
//class MainActivityViewModel @Inject constructor(
//    private val accountService: AccountService
//) : ViewModel() {
//
////
////    var startDestination by mutableStateOf(ScreenRoute.SplashScreen.route)
////        private set
//
//    val hasUser = if(accountService.hasUser()){
//        ScreenRoute.SignInScreen.route
//    }else{
//        ScreenRoute.TopLevelScreenRoute.route
//    }
//
//
//}