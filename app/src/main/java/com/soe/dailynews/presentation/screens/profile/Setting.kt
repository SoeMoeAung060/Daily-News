package com.soe.dailynews.presentation.screens.profile

import android.content.Context
import com.soe.dailynews.R
import com.soe.dailynews.domain.model.DarkMode

//DarkMode
fun Context.darkMode(
    isDarkModeEnabled: Boolean
) = DarkMode(
    title = getString(R.string.dark_mode),
    description =
    if (isDarkModeEnabled) getString(R.string.dark_mode_on)
    else getString(R.string.dark_mode_off),
    isDarkModeEnabled = isDarkModeEnabled,
    icon =
    if (isDarkModeEnabled) R.drawable.mode
    else R.drawable.light_mode
)