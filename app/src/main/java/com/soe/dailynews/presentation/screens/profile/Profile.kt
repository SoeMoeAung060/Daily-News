package com.soe.dailynews.presentation.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soe.dailynews.R
import com.soe.dailynews.navigations.navigation.ScreenRoute.AuthRoute.route
import com.soe.dailynews.presentation.commom.CardLabelTextSmall
import com.soe.dailynews.presentation.commom.DescriptionTextSmall
import com.soe.dailynews.presentation.commom.ScreenTitleTextLarge
import com.soe.dailynews.presentation.commom.TopBar
import com.soe.dailynews.presentation.screens.profile.component.UserProfile
import com.soe.dailynews.presentation.screens.profile.component.UserSetting
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {



    Column(
        modifier = modifier.fillMaxSize()
    ){
        TopAppBar(
            title = {
                ScreenTitleTextLarge(textResId = R.string.account_center)
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),

        )

        Column(
            modifier = modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.background),
        ){

            UserProfile(
                email = stringResource(R.string.email) ,
                onProfileClick = {},
                profileName = stringResource(R.string.Michel_Alysa),
                profilePicture = painterResource(id = R.drawable.elon),
            )

            Spacer(modifier = modifier.height(20.dp))

            CardLabelTextSmall(
                modifier = modifier.fillMaxWidth().padding(start = paddingMedium),
                text = stringResource(R.string.my_account),
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = modifier.height(8.dp))



            UserSetting(
                settingName = stringResource(R.string.personal_info),
                onClick = {}
            )

            UserSetting(
                settingName = stringResource(R.string.language),
                onClick = {}
            )

            UserSetting(
                settingName = stringResource(R.string.dark_mode),
                onClick = {}
            )

            UserSetting(
                settingName = stringResource(R.string.log_out),
                onClick = {
//                    viewModel.signOut(openAndPopUp = openAndPopUp)

                }
            )



        }
    }


}


@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    DailyNewsTheme {
        ProfileScreen(
        )

    }

}