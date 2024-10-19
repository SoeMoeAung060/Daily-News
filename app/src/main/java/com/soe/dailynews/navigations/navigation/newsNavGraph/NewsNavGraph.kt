//package com.soe.dailynews.navigations.navigation.newsNavGraph
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.navigation
//import androidx.navigation.compose.rememberNavController
//import com.soe.dailynews.navigations.NewsNavController
//import com.soe.dailynews.navigations.navigation.ScreenRoute
//import com.soe.dailynews.navigations.navigation.nestedGraph.authNestedGraph
//import com.soe.dailynews.presentation.screens.splashScreen.SplashScreen
//
//@Composable
//fun NewsNavGraph(
//    startDestination : String
//) {
//
//    val navController = rememberNavController()
//    val newsNavController = NewsNavController(navController)
//
//
//    NavHost(
//        navController = navController,
//        startDestination = startDestination
//    ){
//        navigation(
//            route = ScreenRoute.AuthRoute.route,
//            startDestination = ScreenRoute.AuthScreenRout.route
//        ){
//            composable(
//                route = ScreenRoute.AuthScreenRout.route
//            ){
//                authNestedGraph(newsNavController)
//            }
//        }
//
//
//
//        navigation(
//            route = ScreenRoute.TopLevelRoute.route,
//            startDestination = ScreenRoute.TopLevelScreenRoute.route
//        ){
//            composable(
//                route = ScreenRoute.TopLevelScreenRoute.route
//            ){
//                NewsNavigator()
//            }
//        }
//
//
//
//    }
//
//}