package com.soe.dailynews.presentation.screens.explore.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.cornerMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingSmall

@Composable
fun ExploreCategory(
    modifier: Modifier = Modifier,
    categories : List<String>,
    onCategorySelected : (String) -> Unit,
    selectedCategory : String
) {

    Column(
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = paddingSmall)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            categories.forEach { category ->
                OutlinedButton(
                    onClick = {
                        onCategorySelected(category)
                    },
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor =
                            if(selectedCategory == category){
                                MaterialTheme.colorScheme.primaryContainer
                            }else{
                                Color.Transparent
                            }
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                    shape = RoundedCornerShape(cornerMedium),
                ) {
                    Text(
                        text = category,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun ExploreCategoryPreview() {
    val categories = listOf("Category", "Android", "Compose", "Jetpack", "Compose")
    val selectedCategory = "Category"

    DailyNewsTheme {
        ExploreCategory(
            categories = categories,
            onCategorySelected = {categories[0]},
            selectedCategory = selectedCategory
        )
    }
}