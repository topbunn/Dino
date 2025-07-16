package ru.topbun.minecraft_mods_pe.presentation

import android.Manifest.permission.QUERY_ALL_PACKAGES
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.navigator.Navigator
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ru.topbun.minecraft_mods_pe.presentation.screens.tabs.TabsScreen
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.colorScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            requestPermissionNotify()
            MaterialTheme(colorScheme) {
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(Colors.BLACK_BG, false)
                Navigator(TabsScreen)
            }
        }
    }

    @Composable
    private fun requestPermissionNotify(){
        val contract = ActivityResultContracts.RequestMultiplePermissions()
        val launcher = rememberLauncherForActivityResult(contract = contract) {}
        SideEffect {
            launcher.launch(arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, QUERY_ALL_PACKAGES) )
        }
    }


}

