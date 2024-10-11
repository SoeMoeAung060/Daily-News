package com.soe.dailynews

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.soe.dailynews.navigation.DETAIL_SCREEN
import com.soe.dailynews.navigation.FORGOT_PASSWORD_SCREEN
import com.soe.dailynews.navigation.HOME_SCREEN
import com.soe.dailynews.navigation.NEWS_DEFAULT_ID
import com.soe.dailynews.navigation.NEWS_ID
import com.soe.dailynews.navigation.NEWS_ID_WITH_ARG
import com.soe.dailynews.navigation.NewsAppState
import com.soe.dailynews.navigation.SIGN_IN_SCREEN
import com.soe.dailynews.navigation.SIGN_UP_SCREEN
import com.soe.dailynews.navigation.SPLASH_SCREEN
import com.soe.dailynews.screens.home_screen.HomeScreen
import com.soe.dailynews.screens.signin_signup_screen.forgetPasswordScreen.ForgotPasswordScreen
import com.soe.dailynews.screens.signin_signup_screen.signUp.SignUpScreen
import com.soe.dailynews.screens.signin_signup_screen.signin.SignInScreen
import com.soe.dailynews.screens.splash_screen.SplashScreen
import com.soe.dailynews.ui.theme.DailyNewsTheme


@Composable
fun NewsApp() {
    DailyNewsTheme {
        Surface(color = MaterialTheme.colorScheme.background) {

            val appState = rememberAppState()

            Scaffold { innerPadding ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    newsNavGraph(appState)
                }
            }
        }

    }
}


@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) =
    remember(navController) {
        NewsAppState(navController)
    }


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.newsNavGraph(newsAppState: NewsAppState) {

    composable(HOME_SCREEN) {
        HomeScreen(
            openAndPopUp = { route, popUp ->
                newsAppState.navigateAndPopUp(route, popUp)
            }
        )
    }

    composable(DETAIL_SCREEN) {

    }

    composable(SPLASH_SCREEN) {
        SplashScreen(
            openAndPopUp = { route, popUp ->
                newsAppState.navigateAndPopUp(route, popUp)
            }
        )
    }

    composable(FORGOT_PASSWORD_SCREEN) {
        ForgotPasswordScreen(
        )
    }

    composable(SIGN_IN_SCREEN) {
        SignInScreen(
            openAndPopUp = { route, popUp ->
                newsAppState.navigateAndPopUp(route, popUp)
            },
            onForgotPasswordClick = {
                newsAppState.navigate(FORGOT_PASSWORD_SCREEN)
            },

        )
    }



    composable(SIGN_UP_SCREEN) {

        SignUpScreen(
            openAndPopUp = { route, popUp ->
                newsAppState.navigateAndPopUp(route, popUp)
            }
        )
    }
}
