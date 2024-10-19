package com.soe.dailynews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.soe.dailynews.navigations.NewsNavController
import com.soe.dailynews.navigations.navigation.RootNavGraph
import com.soe.dailynews.presentation.ui.theme.DailyNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()
        enableEdgeToEdge()
        setContent {

            DailyNewsTheme {
                val navController = rememberNavController()
                val newsNavController = NewsNavController(navController)

                RootNavGraph(newsNavController)

            }
        }
    }
}
