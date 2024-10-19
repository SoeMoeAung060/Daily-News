package com.soe.dailynews.navigations

import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.soe.dailynews.domain.model.Article
import com.soe.dailynews.navigations.navigation.ScreenRoute


@Stable
class NewsNavController(
    val navController : NavHostController){

    fun popUp(){
        navController.popBackStack()
    }

    fun navigate(route : String){
        navController.navigate(route){
            launchSingleTop = true
        }
    }

    fun navigateAndPopUp(route: String, popUp: String){
        navController.navigate(route){
            launchSingleTop = true
            popUpTo(popUp){
                inclusive = true
            }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) {
                inclusive = true
            }
        }

    }


     fun navigateToDetails(article: Article){
        navController.currentBackStackEntry?.savedStateHandle?.set(
            key = "article",
            value = article)
        navController.navigate(
            route = ScreenRoute.DetailScreen.route
        )
    }

}

