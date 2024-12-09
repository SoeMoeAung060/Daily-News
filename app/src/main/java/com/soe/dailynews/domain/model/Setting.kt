package com.soe.dailynews.domain.model

import androidx.annotation.DrawableRes


data class DarkMode(
    val title: String,
    val description: String,
    @DrawableRes val icon: Int,
    val isDarkModeEnabled: Boolean
)