package com.soe.dailynews.presentation.screens.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.DarkMode
import com.soe.dailynews.navigations.navigation.ScreenRoute
import com.soe.dailynews.presentation.commom.GenericToggle
import com.soe.dailynews.presentation.commom.OptionTitleText
import com.soe.dailynews.presentation.screens.profile.darkMode
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.iconExtraLarge
import com.soe.dailynews.presentation.ui.theme.Dimensions.iconLarge

@Composable
fun SettingOptionItem(
    modifier: Modifier = Modifier,
    onLanguageClick: () -> Unit = {},
    darkMode: DarkMode,
    onToggleClick: (Boolean) -> Unit,
    onLogoutClick: () -> Unit,
    navigateToPersonalScreen: () -> Unit
) {


    LazyColumn {


        //Personal
        item {
            ListItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable { navigateToPersonalScreen() },
                headlineContent = {
                    OptionTitleText(
                        text = stringResource(R.string.personal)
                    )
                },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.personal),
                        contentDescription = stringResource(R.string.personal),
                        modifier = Modifier.size(iconExtraLarge)
                    )
                },
                trailingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = stringResource(R.string.arrow),
                        modifier = Modifier.size(iconLarge)

                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )

            )
        }


        //Language
        item {
            ListItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onLanguageClick()
                    },
                headlineContent = {
                    OptionTitleText(
                        text = stringResource(R.string.language)
                    )
                },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.language),
                        contentDescription = stringResource(R.string.language),
                        modifier = Modifier.size(iconExtraLarge)
                    )
                },
                trailingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = stringResource(R.string.arrow),
                        modifier = Modifier.size(iconLarge)

                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )

            )
        }


        //Mode
        item {
            ListItem(
                modifier = modifier
                    .fillMaxWidth(),
                headlineContent = {
                    OptionTitleText(
                        text = stringResource(R.string.dark_mode)
                    )
                },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.mode),
                        contentDescription = stringResource(R.string.dark_mode),
                        modifier = Modifier.size(iconExtraLarge)
                    )
                },
                trailingContent = {
                    GenericToggle(
                        modifier = Modifier.testTag("Dark Mode Toggle"),
                        isChecked = darkMode.isDarkModeEnabled,
                        onCheckedChange = {
                            onToggleClick(it)

                        }
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )

            )
        }

        //Logout
        item {
            ListItem(
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onLogoutClick()
                    },
                headlineContent = {
                    OptionTitleText(
                        text = stringResource(R.string.log_out)
                    )
                },
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = stringResource(R.string.log_out),
                        modifier = Modifier.size(iconExtraLarge)
                    )
                },
                trailingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = stringResource(R.string.arrow),
                        modifier = Modifier.size(iconLarge)

                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = Color.Transparent
                )

            )
        }

    }


}

@Preview(showBackground = true)
@Composable
private fun SettingOptionItemPreview() {

    val context = LocalContext.current
    DailyNewsTheme {
        SettingOptionItem(
            onLogoutClick = {},
            onToggleClick = {},
            onLanguageClick = {},
            darkMode = context.darkMode(
                isDarkModeEnabled = isSystemInDarkTheme()
            ),
            navigateToPersonalScreen = {}
        )
    }

}