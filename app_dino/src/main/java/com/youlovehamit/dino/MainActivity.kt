package com.youlovehamit.dino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import ru.topbun.ui.App
import ru.topbun.ui.theme.Colors
import ru.topbun.ui.theme.colorScheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme(colorScheme.copy(primary = Colors.BLUE)) {
                App()
            }
        }
    }
}