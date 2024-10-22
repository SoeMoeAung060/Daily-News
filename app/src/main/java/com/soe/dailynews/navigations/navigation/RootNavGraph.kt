package com.soe.dailynews.navigations.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.soe.dailynews.navigations.NewsNavController
import com.soe.dailynews.navigations.navigation.nestedGraph.TopLevelScreenGraph
import com.soe.dailynews.navigations.navigation.nestedGraph.authNestedGraph


@Composable
fun RootNavGraph(
    newsNavController: NewsNavController,
) {

    NavHost(
        modifier = Modifier
            .padding()
            .background(Color.White),
        navController = newsNavController.navController,
        startDestination = ScreenRoute.AuthRoute.route
    ) {


        authNestedGraph(newsNavController)

        composable(route = ScreenRoute.TopLevelScreenRoute.route) {
            TopLevelScreenGraph()

        }
    }
}

