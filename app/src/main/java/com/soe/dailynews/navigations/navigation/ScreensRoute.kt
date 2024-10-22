package com.soe.dailynews.navigations.navigation

import com.soe.dailynews.R

sealed class ScreenRoute(val route : String) {


    data object SplashScreen : ScreenRoute("splash_screen")
    data object SignInScreen : ScreenRoute("signIn_screen")
    data object SignUpScreen : ScreenRoute("signup_screen")
    data object ForgotPasswordScreen : ScreenRoute("forgot_password_screen")


    data object HomeScreen : ScreenRoute("home_screen")
    data object BookmarkScreen : ScreenRoute("bookmark_screen")
    data object ProfileScreen : ScreenRoute("profile_screen")
    data object ExploreScreen : ScreenRoute("explore_screen")


    data object AuthRoute : ScreenRoute("auth")
    data object TopLevelScreenRoute : ScreenRoute("top_level_screen_route")

    data object DetailScreen : ScreenRoute("detail/{$NEWS_ID}") {
        fun createRoute(itemId: String)  : String {
            return this.route.replace(oldValue = "{$NEWS_ID}", newValue = itemId)
        }
    }
}


const val NEWS_ID = "news_id"
const val NEWS_DEFAULT_ID = "-1"
const val NEWS_ID_WITH_ARG = "?$NEWS_ID=$NEWS_ID"


//
//
//sealed class BottomBarScreenRoute(val route: String, val title: String, val icon: Int) {
//
//    data object Home : BottomBarScreenRoute(
//        route = "home",
//        title = "Home",
//        icon = R.drawable.home
//    )
//
//    data object Bookmark : BottomBarScreenRoute(
//        route = "bookmark",
//        title = "Bookmark",
//        icon = R.drawable.bookmark
//    )
//
//    data object Profile : BottomBarScreenRoute(
//        route = "profile",
//        title = "Profile",
//        icon = R.drawable.profile
//    )
//
//
//}