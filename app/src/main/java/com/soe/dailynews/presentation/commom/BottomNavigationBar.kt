package com.soe.dailynews.presentation.commom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.soe.dailynews.R
import com.soe.dailynews.navigations.navigation.nestedGraph.BottomNavigationItem
import com.soe.dailynews.presentation.ui.theme.Dimensions.bottomNavigationItemHeight


@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    selectedItem : Int,
    onClick : (Int) -> Unit,) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        tonalElevation = 5.dp,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onClick(index) },
                icon = {
                    Column(
                        modifier = Modifier.height(bottomNavigationItemHeight),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null
                        )
                        if (index == selectedItem) {
                            Text(text = item.title, style = MaterialTheme.typography.labelSmall)
                        }
                    }

                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                    unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }

}
//
//@Composable
//fun BottomNavigationBar(
//    navController: NavHostController,
//) {
//
//    val items = listOf(
//        BottomBarScreenRoute.Home.route,
//        BottomBarScreenRoute.Bookmark.route,
//        BottomBarScreenRoute.Profile.route
//    )
//
//    val selectedIcon = listOf(
//        R.drawable.home_filled, R.drawable.bookmark_filled, R.drawable.profile_filled
//    )
//
//    val unSelectedIcon = listOf(
//        R.drawable.home, R.drawable.bookmark, R.drawable.profile
//    )
//
//
//    var selectedItem by remember { mutableIntStateOf(0) }
//
//
//    NavigationBar {
//        items.forEachIndexed{index, item ->
//            NavigationBarItem(
//                icon = {
//                    Icon(
//                        painter = if (selectedItem == index) painterResource(selectedIcon[index]) else painterResource(unSelectedIcon[index]),
//                        contentDescription = item
//                    )
//                },
//                label = { Text(item) },
//                selected = selectedItem == index,
//                onClick = {
//                    selectedItem = index
//                    navController.navigate(items[index]){
//                        popUpTo(navController.graph.startDestinationId){
//                            saveState = true
//                        }
//                        launchSingleTop = true
//                        restoreState = true
//                    }
//                }
//            )
//        }
//    }
//
//}