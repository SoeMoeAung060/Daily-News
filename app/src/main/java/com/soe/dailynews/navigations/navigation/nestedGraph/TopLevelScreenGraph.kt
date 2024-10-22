package com.soe.dailynews.navigations.navigation.nestedGraph

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.soe.dailynews.R
import com.soe.dailynews.data.mapper.toBookmarkArticle
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.navigations.NewsNavController
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.navigations.navigation.ScreenRoute.AuthRoute
import com.soe.dailynews.presentation.commom.BottomNavigationBar
import com.soe.dailynews.presentation.screens.bookmark.BookmarkScreen
import com.soe.dailynews.presentation.screens.bookmark.BookmarkViewmodel
import com.soe.dailynews.presentation.screens.detail_screen.DetailScreen
import com.soe.dailynews.presentation.screens.explore.ExploreScreen
import com.soe.dailynews.presentation.screens.explore.ExploreViewModel
import com.soe.dailynews.presentation.screens.explore.component.ExploreList
import com.soe.dailynews.presentation.screens.home.HomeScreen
import com.soe.dailynews.presentation.screens.profile.ProfileScreen
import kotlin.math.exp


data class BottomNavigationItem(
    val title: String,
    @DrawableRes val icon: Int,
)

@Composable
fun TopLevelScreenGraph(
) {

    val navController = rememberNavController()
    val newsNavController = NewsNavController(navController)
    val backStackState = navController.currentBackStackEntryAsState()


    val home = stringResource(R.string.home)
    val explore = stringResource(R.string.explore)
    val bookmark = stringResource(R.string.bookmark)
    val profile = stringResource(R.string.my_profile)

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.home, title = home),
            BottomNavigationItem(icon = R.drawable.explore, title = explore),
            BottomNavigationItem(icon = R.drawable.bookmark, title = bookmark),
            BottomNavigationItem(icon = R.drawable.profile, title = profile)
        )
    }


    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backStackState.value) {
        when(backStackState.value?.destination?.route){
            ScreenRoute.HomeScreen.route -> 0
            ScreenRoute.ExploreScreen.route -> 1
            ScreenRoute.BookmarkScreen.route -> 2
            ScreenRoute.ProfileScreen.route -> 3
            else -> 0
        }
    }

    val isBottomBarVisible = remember (key1 = backStackState.value){
        backStackState.value?.destination?.route == ScreenRoute.HomeScreen.route ||
                backStackState.value?.destination?.route == ScreenRoute.ExploreScreen.route ||
                backStackState.value?.destination?.route == ScreenRoute.BookmarkScreen.route ||
                backStackState.value?.destination?.route == ScreenRoute.ProfileScreen.route
    }


    Scaffold(

        modifier = Modifier.fillMaxSize(),
        contentColor = Color.Transparent,
        bottomBar = {
            AnimatedVisibility(
                visible = isBottomBarVisible,
                enter = slideInVertically(initialOffsetY = { it }),
                exit = slideOutVertically(targetOffsetY = { it }),
            ) {
                BottomNavigationBar(
                    items = bottomNavigationItem,
                    selectedItem = selectedItem,
                    onClick = { index ->
                        when (index) {
                            0 -> navigationToTop(
                                navController = navController,
                                route = ScreenRoute.HomeScreen.route
                            )

                            1 -> navigationToTop(
                                navController = navController,
                                route = ScreenRoute.ExploreScreen.route
                            )

                            2 -> navigationToTop(
                                navController = navController,
                                route = ScreenRoute.BookmarkScreen.route
                            )

                            3 -> navigationToTop(
                                navController = navController,
                                route = ScreenRoute.ProfileScreen.route
                            )
                        }
                    }
                )
            }
        }
    ) {

        NavHost(
            modifier = Modifier.padding(
                bottom = it.calculateBottomPadding()),
            navController = newsNavController.navController,
            startDestination = ScreenRoute.HomeScreen.route
        ){
            composable(
                ScreenRoute.HomeScreen.route
            ) {
                HomeScreen(
                    navigateToDetail = { article ->
                        newsNavController.navigateToDetails(article = article)
                    },
                )
            }

            composable(ScreenRoute.BookmarkScreen.route) {

                val viewModel : BookmarkViewmodel = hiltViewModel()
                val state = viewModel.state.value

                BookmarkScreen(
                    state = state,
                    navigateToDetail = { article ->
                        newsNavController.navigateToDetails(article = article)
                    }
                )
            }

            composable(ScreenRoute.ProfileScreen.route) {
                ProfileScreen(
//                    openAndPopUp = { route, popUp ->
//                    newsNavController.navigateAndPopUp(route, popUp)
//                    Log.d("Navigation", "Navigating to: ${AuthRoute.route} with popUp: $popUp")

                )
            }

            composable(
                ScreenRoute.DetailScreen.route
            ) {
                val result = newsNavController.navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                Log.d("TAG", "result: $result")

                result?.let { article ->
                    DetailScreen(
                        article = article,
                        popUp = {
                            newsNavController.popUp()
                            newsNavController.navController.currentBackStackEntry?.savedStateHandle?.remove<Article>("article")
                        },
                    )
                }
            }


            composable(
                ScreenRoute.ExploreScreen.route
            ) {



                val viewModel : ExploreViewModel = hiltViewModel()
                val articles = viewModel.articles.collectAsLazyPagingItems()
                Log.d("Article Explore", "articles explore count: ${articles.itemCount}, loading state: ${articles.loadState.refresh}")

                val exploreState = viewModel.state.value
                val searchedResult = viewModel.searchResult.collectAsLazyPagingItems()


                ExploreScreen(
                    exploreState = exploreState,
                    event = viewModel::onEvent,
                    isRefreshing = viewModel.isRefreshing.value,
                    onRefresh = { viewModel.refreshArticle() },
                    articles = articles,
                    searchResults = searchedResult,
                    navigateToDetails = {article ->
                        newsNavController.navigateToDetails(article)
                    },

                )
            }
        }

    }
}


private fun navigationToTop(navController: NavController, route: String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
