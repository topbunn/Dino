package ru.topbun.minecraft_mods_pe.presentation.screens.splash

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.delay
import ru.topbun.minecraft_mods_pe.R
import ru.topbun.minecraft_mods_pe.presentation.screens.tabs.TabsScreen
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography.APP_TEXT
import ru.topbun.minecraft_mods_pe.presentation.theme.components.InterstitialAd
import ru.topbun.minecraft_mods_pe.presentation.theme.components.ProgressBar

object SplashScreen: Screen {


    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.BLACK_BG)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(ru.topbun.ui.R.string.app_name),
                style = APP_TEXT,
                fontSize = 32.sp,
                fontFamily = Fonts.SF.BOLD,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(30.dp))
            Image(
                modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                painter = painterResource(R.drawable.logo),
                contentDescription = "Image preview",
                contentScale = ContentScale.FillWidth
            )
            Spacer(Modifier.height(50.dp))
            ProgressBar()
        }
    }

    @Composable
    fun ProgressBar() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val activity = LocalActivity.currentOrThrow
            val navigator = LocalNavigator.currentOrThrow
            var progress by rememberSaveable { mutableFloatStateOf(0f) }
            LaunchedEffect(Unit) {
                while (progress < 1){
                    progress += 0.0015f
                    delay(10)
                }
                navigator.push(TabsScreen)
            }
            ProgressBar(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(RoundedCornerShape(2.dp)),
            )
            Spacer(Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = (progress * 100).toInt().toString() + "%",
                style = APP_TEXT,
                fontSize = 14.sp,
                fontFamily = Fonts.SF.MEDIUM,
                textAlign = TextAlign.Center
            )
            InterstitialAd(activity)
        }
    }


}

