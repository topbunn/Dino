package ru.topbun.splash

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.delay
import ru.topbun.navigation.SharedScreen
import ru.topbun.ui.components.InterstitialAd
import ru.topbun.ui.theme.Colors
import ru.topbun.ui.theme.Fonts
import ru.topbun.ui.theme.Typography

object SplashScreen: Screen {


    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(Colors.BLACK_BG)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            val context = LocalContext.current
            val applicationName = context.applicationInfo.labelRes
            val applicationImage = context.applicationInfo.icon
            Text(
                text = stringResource(applicationName),
                style = Typography.APP_TEXT,
                fontSize = 32.sp,
                fontFamily = Fonts.SF.BOLD,
                textAlign = TextAlign.Companion.Center
            )
            Spacer(Modifier.Companion.height(30.dp))
            Image(
                modifier = Modifier.Companion.fillMaxWidth().clip(RoundedCornerShape(8.dp)),
                painter = rememberAsyncImagePainter(applicationImage),
                contentDescription = "Image preview",
                contentScale = ContentScale.Companion.FillWidth
            )
            Spacer(Modifier.Companion.height(50.dp))
            ProgressBar()
        }
    }

    @Composable
    fun ProgressBar() {
        Column(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            val activity = LocalActivity.currentOrThrow
            val navigator = LocalNavigator.currentOrThrow
            var progress by rememberSaveable { mutableFloatStateOf(0f) }

            val tabsScreen = rememberScreen(SharedScreen.TabsScreen)
            LaunchedEffect(Unit) {
                while (progress < 1) {
                    progress += 0.0015f
                    delay(10)
                }
                navigator.push(tabsScreen)
            }
            ru.topbun.ui.components.ProgressBar(
                progress = progress,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(20.dp)
                    .clip(androidx.compose.foundation.shape.RoundedCornerShape(2.dp)),
            )
            Spacer(Modifier.Companion.height(10.dp))
            Text(
                modifier = Modifier.Companion.padding(horizontal = 20.dp),
                text = (progress * 100).toInt().toString() + "%",
                style = Typography.APP_TEXT,
                fontSize = 14.sp,
                fontFamily = Fonts.SF.MEDIUM,
                textAlign = TextAlign.Companion.Center
            )
            InterstitialAd(activity)
        }
    }


}