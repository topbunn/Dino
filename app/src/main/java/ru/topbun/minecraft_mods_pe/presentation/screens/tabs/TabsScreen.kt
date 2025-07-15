package ru.topbun.minecraft_mods_pe.presentation.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import ru.topbun.minecraft_mods_pe.presentation.screens.favorite.FavoriteScreen
import ru.topbun.minecraft_mods_pe.presentation.screens.feedback.FeedbackScreen
import ru.topbun.minecraft_mods_pe.presentation.screens.main.MainScreen
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography
import ru.topbun.minecraft_mods_pe.presentation.theme.components.BottomNavigationItem

object TabsScreen: Screen {

    @Composable
    override fun Content() {
        TabNavigator(tab = MainScreen){
            Scaffold(
                modifier = Modifier.navigationBarsPadding(),
                content = {
                    Box(Modifier.fillMaxSize().padding(it)){
                        CurrentTab()
                    }
                },
                bottomBar = {
                    Column {
                        Spacer(Modifier.fillMaxWidth().height(1.dp).background(Colors.WHITE.copy(0.15f)))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Colors.GRAY_BG)
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            BottomNavigationItem(MainScreen)
                            BottomNavigationItem(FavoriteScreen)
                            BottomNavigationItem(FeedbackScreen)
                        }
                    }
                }
            )
        }
    }

}

