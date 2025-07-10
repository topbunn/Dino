package ru.topbun.minecraft_mods_pe.presentation.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.topbun.domain.R
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography
import ru.topbun.minecraft_mods_pe.presentation.theme.components.ModItem
import ru.topbun.minecraft_mods_pe.presentation.theme.components.noRippleClickable

object FavoriteScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.GRAY_BG)
                .statusBarsPadding()
                .background(Colors.BLACK_BG)

        ) {
            val viewModel = viewModel<FavoriteViewModel>()
            val state by viewModel.state.collectAsState()
            Header(state)
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(20.dp)
            ) {
                if (state.mods.isNotEmpty()){
                    items(items = state.mods, key = {it.id}){
                        ModItem(it){ viewModel.removeFavorite(it) }
                    }
                } else {
                    item {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "The list is empty :(",
                            style = Typography.APP_TEXT,
                            fontSize = 18.sp,
                            color = Colors.GRAY,
                            textAlign = TextAlign.Center,
                            fontFamily = Fonts.SF.BOLD,
                        )
                    }
                }
            }
        }
    }

}

@Composable
private fun Header(state: FavoriteState) {
    val navigator = LocalNavigator.currentOrThrow
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.GRAY_BG)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .height(20.dp)
                .noRippleClickable {
                    navigator.pop()
                },
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "button back",
            tint = Colors.GREEN
        )
        Text(
            text = stringResource(ru.topbun.minecraft_mods_pe.R.string.favorite, state.mods.count()),
            style = Typography.APP_TEXT,
            fontSize = 18.sp,
            color = Colors.GRAY,
            fontFamily = Fonts.SF.BOLD,
        )
        Box {}
    }
}