package com.soe.dailynews.presentation.screens.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.soe.dailynews.R
import com.soe.dailynews.presentation.commom.CardTitleTextSmall
import com.soe.dailynews.presentation.commom.IconNormal
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingNormal

@Composable
fun UserSetting(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    settingName : String,


) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = paddingMedium)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = modifier.fillMaxWidth().padding(vertical = paddingNormal),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            CardTitleTextSmall(
                text = settingName,
                modifier = modifier
            )
            IconNormal(
                modifier= modifier,
                painter = painterResource(R.drawable.arrow),
                contentDescription = stringResource(R.string.arrow)
            )
        }

    }

}






@Preview(showBackground = true)
@Composable
private fun UserSettingPreview() {
    DailyNewsTheme {
        UserSetting(
            onClick = {},
            settingName = "Personal Info",
        )

    }

}

