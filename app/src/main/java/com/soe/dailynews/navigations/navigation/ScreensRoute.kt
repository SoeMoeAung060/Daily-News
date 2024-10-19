package com.soe.dailynews.navigations.navigation

import com.soe.dailynews.R

sealed class ScreenRoute(val route : String) {


    data object SplashScreen : ScreenRoute("splash_screen")
    data object SignInScreen : ScreenRoute("signIn_screen")
    data object SignUpScreen : ScreenRoute("signup_screen")
    data object ForgotPasswordScreen : ScreenRoute("forgot_password_screen")

    data object AuthRoute : ScreenRoute("auth")
    data object TopLevelScreenRoute : ScreenRoute("top_level")

    data object DetailScreen : ScreenRoute("detail/{itemId}") {
        fun createRoute(itemId: String) = "detail/$itemId"
    }
}


const val NEWS_ID = "news_id"
const val NEWS_DEFAULT_ID = "-1"
const val NEWS_ID_WITH_ARG = "?$NEWS_ID=$NEWS_ID"




sealed class BottomBarScreenRoute(val route: String, val title: String, val icon: Int? = null) {

    data object Home : BottomBarScreenRoute(
        route = "home",
        title = "Home",
        icon = R.drawable.home
    )

    data object Bookmark : BottomBarScreenRoute(
        route = "bookmark",
        title = "Bookmark",
        icon = R.drawable.bookmark
    )

    data object Profile : BottomBarScreenRoute(
        route = "profile",
        title = "Profile",
        icon = R.drawable.profile
    )


}