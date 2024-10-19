//package com.soe.dailynews.navigations.navigation.newsNavGraph
//
//import android.util.Log
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import com.soe.dailynews.domain.model.Article
//import com.soe.dailynews.navigations.NewsNavController
//import com.soe.dailynews.navigations.navigation.BottomBarScreenRoute
//import com.soe.dailynews.navigations.navigation.ScreenRoute
//import com.soe.dailynews.presentation.commom.BottomNavigationBar
//import com.soe.dailynews.presentation.screens.bookmark.BookMarkScreen
//import com.soe.dailynews.presentation.screens.detail_screen.DetailScreen
//import com.soe.dailynews.presentation.screens.home.HomeScreen
//import com.soe.dailynews.presentation.screens.profile.ProfileScreen
//
//@Composable
//fun NewsNavigator(modifier: Modifier = Modifier) {
//
//    val navController = rememberNavController()
//    val newsNavController = NewsNavController(navController)
//    val backStackState = navController.currentBackStackEntryAsState()
//
//
//    Scaffold(
//        bottomBar = {
//            BottomNavigationBar(newsNavController.navController)
//        }
//
//    ) { innerPadding->
//
//
//        NavHost(
//            modifier = Modifier.padding(innerPadding),
//            navController = navController,
//            startDestination = BottomBarScreenRoute.Home.route
//        ){
//
//            composable(
//                BottomBarScreenRoute.Home.route
//            ) {
//                HomeScreen(
//                    navigateToDetail = { article ->
//                        newsNavController.navigateToDetails(article = article)
//                    },
//                )
//            }
//
//            composable(BottomBarScreenRoute.Bookmark.route) {
//                BookMarkScreen()
//            }
//
//            composable(BottomBarScreenRoute.Profile.route) {
//                ProfileScreen(openAndPopUp = { route, popUp ->
//                    newsNavController.navigateAndPopUp(route, popUp)
//                })
//            }
//
//            composable(
//                ScreenRoute.DetailScreen.route
//            ) {
//                val result = newsNavController.navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
//                Log.d("TAG", "result: $result")
//
//                result?.let { article ->
//                    DetailScreen(
//                        article = article,
//                        popUp = {
//                            newsNavController.popUp()
//                            newsNavController.navController.currentBackStackEntry?.savedStateHandle?.remove<Article>("article")
//
//                        }
//                    )
//                }
//            }
//
//        }
//
//    }
//
//}