package com.soe.dailynews.navigations.navigation.nestedGraph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.soe.dailynews.navigations.NewsNavController
import com.soe.dailynews.navigations.navigation.BottomBarScreenRoute
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.presentation.screens.bookmark.BookMarkScreen
import com.soe.dailynews.presentation.screens.detail_screen.DetailScreen
import com.soe.dailynews.presentation.screens.home.HomeScreen
import com.soe.dailynews.presentation.screens.home.HomesScreenViewModel
import com.soe.dailynews.presentation.screens.profile.ProfileScreen

fun NavGraphBuilder.topLevelNestedGraph(
    newsNavController: NewsNavController
){



    navigation(
        startDestination = BottomBarScreenRoute.Home.route,
        route = ScreenRoute.TopLevelScreenRoute.route
    ){

        composable(BottomBarScreenRoute.Home.route) {
            HomeScreen(
                navigateToDetail = { article ->
                    newsNavController.navigate(ScreenRoute.DetailScreen.route)
                },
            )
        }

        composable(BottomBarScreenRoute.Bookmark.route) {
            BookMarkScreen()
        }

        composable(BottomBarScreenRoute.Profile.route) {
            ProfileScreen(openAndPopUp = { route, popUp ->
                newsNavController.navigateAndPopUp(route, popUp)
            })
        }

        composable(ScreenRoute.DetailScreen.route) {
            DetailScreen()
        }



//        composable(
//        route = "${ScreenRoute.DetailScreen.route}?url={url}",
//        arguments = listOf(navArgument("url") { type = NavType.StringType })
//    ) {
//        DetailScreen(
//            url = it.arguments?.getString("url") ?: ""
//        )
//    }

    }
}