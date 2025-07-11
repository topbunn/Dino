package ru.topbun.minecraft_mods_pe.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.topbun.minecraft_mods_pe.presentation.screens.instruction.InstructionScreen
import ru.topbun.minecraft_mods_pe.presentation.screens.main.MainScreen
import ru.topbun.minecraft_mods_pe.presentation.screens.splash.SplashScreen
import ru.topbun.minecraft_mods_pe.presentation.screens.tabs.TabsScreen
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.colorScheme

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

