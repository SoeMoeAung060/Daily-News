package com.soe.dailynews.navigations.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.soe.dailynews.navigations.NewsNavController
import com.soe.dailynews.navigations.navigation.nestedGraph.authNestedGraph
import com.soe.dailynews.navigations.navigation.nestedGraph.topLevelNestedGraph


@Composable
fun RootNavGraph(
    newsNavController: NewsNavController,
) {
    val navController = newsNavController.navController
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavHost(
        modifier = Modifier
            .padding()
            .background(Color.White),
        navController = newsNavController.navController,
        startDestination = ScreenRoute.AuthRoute.route
    ) {


        authNestedGraph(newsNavController)

        topLevelNestedGraph(newsNavController)
    }
}

