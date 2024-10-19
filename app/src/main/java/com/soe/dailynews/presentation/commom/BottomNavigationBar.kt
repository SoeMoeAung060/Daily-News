package com.soe.dailynews.presentation.commom

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.soe.dailynews.R
import com.soe.dailynews.navigations.navigation.BottomBarScreenRoute


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
) {

    val items = listOf(
        BottomBarScreenRoute.Home.route,
        BottomBarScreenRoute.Bookmark.route,
        BottomBarScreenRoute.Profile.route
    )

    val selectedIcon = listOf(
        R.drawable.home_filled, R.drawable.bookmark_filled, R.drawable.profile_filled
    )

    val unSelectedIcon = listOf(
        R.drawable.home, R.drawable.bookmark, R.drawable.profile
    )


    var selectedItem by remember { mutableIntStateOf(0) }


    NavigationBar {
        items.forEachIndexed{index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = if (selectedItem == index) painterResource(selectedIcon[index]) else painterResource(unSelectedIcon[index]),
                        contentDescription = item
                    )
                },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(items[index]){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}