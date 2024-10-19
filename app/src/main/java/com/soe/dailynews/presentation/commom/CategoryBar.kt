package com.soe.dailynews.presentation.commom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soe.dailynews.presentation.screens.home.HomesScreenViewModel
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme



@Composable
fun CategoryBar(
    homesScreenViewModel: HomesScreenViewModel = hiltViewModel()
) {
//
//
//    val selectedItem = remember { mutableStateOf(false) }
//    val category = listOf(
//        "General",
//        "Business",
//        "Entertainment",
//        "Health",
//        "Science",
//        "Sports",
//        "Technology"
//    )
//
//    LazyRow(
//        modifier = Modifier
//            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        items(category.size) { index ->
//            val currentCategory = category[index]
//
//            // Wrap Button inside a Box or Column for proper alignment
//            Box(
//                modifier = Modifier.padding(horizontal = 4.dp)
//            ) {
//                Button(
//                    onClick = {
////                        homesScreenViewModel.setSelectedCategory(index)
////                        homesScreenViewModel.getNewsTopHeadLines(currentCategory)
//                    },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = if (selectedItem == index) {
//                            MaterialTheme.colorScheme.primary
//                        } else {
//                            MaterialTheme.colorScheme.surface
//                        }
//                    ),
//                    elevation = ButtonDefaults.buttonElevation(
//                        defaultElevation = 1.dp,
//                        pressedElevation = 0.dp
//                    ),
//                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
//                ) {
//                    Text(
//                        text = currentCategory,
//                        color = if (selectedItem == index) {
//                            MaterialTheme.colorScheme.onPrimary
//                        } else {
//                            MaterialTheme.colorScheme.primary
//                        }
//                    )
//                }
//            }
//        }
//    }
}

@Preview(showBackground = true)
@Composable
private fun CategoryBarPreview() {
    DailyNewsTheme {
        CategoryBar()
    }

}