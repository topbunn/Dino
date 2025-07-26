package ru.topbun.ui

import android.Manifest
import android.window.SplashScreen
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.topbun.navigation.SharedScreen
import ru.topbun.ui.theme.Colors
import ru.topbun.ui.theme.colorScheme

@Composable
fun App() {
    requestPermission()
    val initScreen = rememberScreen(SharedScreen.SplashScreen)
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Colors.BLACK_BG, false)
    Navigator(initScreen)
}

@Composable
private fun requestPermission(){
    val contract = ActivityResultContracts.RequestMultiplePermissions()
    val launcher = rememberLauncherForActivityResult(contract = contract) {}
    SideEffect {
        launcher.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.QUERY_ALL_PACKAGES
            )
        )
    }
}