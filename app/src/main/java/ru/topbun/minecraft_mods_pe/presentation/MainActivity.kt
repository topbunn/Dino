package ru.topbun.minecraft_mods_pe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.yandex.mobile.ads.nativeads.NativeAdLoader
import ru.topbun.minecraft_mods_pe.presentation.screens.splash.SplashScreen
import ru.topbun.minecraft_mods_pe.presentation.screens.tabs.TabsScreen
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.colorScheme
import ru.topbun.minecraft_mods_pe.presentation.theme.components.AdaptiveInlineBanner
import ru.topbun.minecraft_mods_pe.presentation.theme.components.InterstitialAd
import ru.topbun.minecraft_mods_pe.utills.ads.AppOpenAdManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(colorScheme) {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(Colors.BLACK_BG, false)
                Navigator(SplashScreen)
            }
        }
    }
}
