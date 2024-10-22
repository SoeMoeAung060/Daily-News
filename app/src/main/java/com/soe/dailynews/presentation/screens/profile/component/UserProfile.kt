package com.soe.dailynews.presentation.screens.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.soe.dailynews.R
import com.soe.dailynews.presentation.commom.IconButtonNormal
import com.soe.dailynews.presentation.commom.ProfileDescription
import com.soe.dailynews.presentation.commom.ProfileName
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingMedium
import com.soe.dailynews.presentation.ui.theme.Dimensions.paddingNormal
import com.soe.dailynews.presentation.ui.theme.Dimensions.profilePictureSize


@Composable
fun UserProfile(
    modifier: Modifier = Modifier,
    email : String,
    profileName : String,
    profilePicture : Painter,
    onProfileClick : () -> Unit,
    ) {

    Row(
        modifier = modifier.fillMaxWidth().padding(paddingMedium),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = modifier.size(profilePictureSize)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
        ){
            Image(
                painter = profilePicture,
                contentDescription = null,
                modifier = modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        }

        Spacer(modifier = modifier.width(paddingNormal))

        Column(){
            Row(
                modifier = modifier.padding(end = paddingMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                ProfileName(
                    text = profileName,
                    color = MaterialTheme.colorScheme.onSurface
                )
                IconButtonNormal(
                    painter = painterResource(R.drawable.edit),
                    contentDescription = stringResource(R.string.edit_profile),
                    onClick = onProfileClick
                )
            }


            ProfileDescription(
                text = email,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun AccountCenterPreview() {
    DailyNewsTheme {
        UserProfile(
            email = "dailynews@gmail.com",
            profileName = "Soe Moe Aung",
            profilePicture = painterResource( R.drawable.elon),
            modifier = Modifier,
            onProfileClick = {}
        )
    }
    
}