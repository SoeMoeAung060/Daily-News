//package com.soe.dailynews.navigations.navigation.nestedGraph
//
//import android.util.Log
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavType
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.navigation
//import androidx.navigation.navArgument
//import com.soe.dailynews.domain.model.Article
//import com.soe.dailynews.navigations.NewsNavController
//import com.soe.dailynews.navigations.navigation.BottomBarScreenRoute
//import com.soe.dailynews.navigations.navigation.ScreenRoute
//import com.soe.dailynews.presentation.screens.bookmark.BookMarkScreen
//import com.soe.dailynews.presentation.screens.detail_screen.DetailScreen
//import com.soe.dailynews.presentation.screens.home.HomeScreen
//import com.soe.dailynews.presentation.screens.profile.ProfileScreen
//import com.soe.dailynews.util.dummyArticle
//
//fun NavGraphBuilder.topLevelNestedGraph(
//    newsNavController: NewsNavController
//){
//
//    navigation(
//        startDestination = BottomBarScreenRoute.Home.route,
//        route = ScreenRoute.TopLevelScreenRoute.route
//    ){
//
//        composable(
//            BottomBarScreenRoute.Home.route
//        ) {
//            HomeScreen(
//                navigateToDetail = { article ->
//                    newsNavController.navigateToDetails(article = article)
//                },
//            )
//        }
//
//        composable(BottomBarScreenRoute.Bookmark.route) {
//            BookMarkScreen()
//        }
//
//        composable(BottomBarScreenRoute.Profile.route) {
//            ProfileScreen(openAndPopUp = { route, popUp ->
//                newsNavController.navigateAndPopUp(route, popUp)
//            })
//        }
//
//        composable(
//            ScreenRoute.DetailScreen.route
//    ) {
//            val result = newsNavController.navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
//            Log.d("TAG", "result: $result")
//
//            result?.let { article ->
//                DetailScreen(
//                    article = article,
//                    popUp = {
//                        newsNavController.popUp()
//                        newsNavController.navController.currentBackStackEntry?.savedStateHandle?.remove<Article>("article")
//
//                    }
//                )
//            }
//    }
//
//    }
//}
//
