package com.soe.dailynews.navigations.navigation.nestedGraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.soe.dailynews.navigations.NewsNavController
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.presentation.screens.signin_signup.forgetPasswordScreen.ForgotPasswordScreen
import com.soe.dailynews.presentation.screens.signin_signup.signIn.SignInScreen
import com.soe.dailynews.presentation.screens.signin_signup.signUp.SignUpScreen
import com.soe.dailynews.presentation.screens.splashScreen.SplashScreen

fun NavGraphBuilder.authNestedGraph(
    newsNavController: NewsNavController
){
    navigation(
        startDestination = ScreenRoute.SplashScreen.route,
        route = ScreenRoute.AuthRoute.route){

        composable(ScreenRoute.SplashScreen.route) {
            SplashScreen(openAndPopUp = { route, popUp ->
                newsNavController.navigateAndPopUp(route, popUp)
            })
        }

        composable(ScreenRoute.SignInScreen.route) {
            SignInScreen(
                openAndPopUp = { route, popUp ->
                    newsNavController.navigateAndPopUp(route, popUp)
                },
                onForgotPasswordClick = {
                    newsNavController.navigate("")
                },

                )
        }

        composable(ScreenRoute.SignUpScreen.route) {
            SignUpScreen(openAndPopUp = { route, popUp ->
                newsNavController.navigateAndPopUp(route, popUp)
            })
        }

        composable(ScreenRoute.ForgotPasswordScreen.route) {
            ForgotPasswordScreen()
        }
    }
}