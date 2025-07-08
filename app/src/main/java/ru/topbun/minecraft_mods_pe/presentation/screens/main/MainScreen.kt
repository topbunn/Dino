package ru.topbun.minecraft_mods_pe.presentation.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import ru.topbun.domain.R
import ru.topbun.domain.entity.SortType
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.components.AppDropDown
import ru.topbun.minecraft_mods_pe.presentation.theme.components.AppTextField
import ru.topbun.minecraft_mods_pe.presentation.theme.components.ModItem
import ru.topbun.minecraft_mods_pe.presentation.theme.components.TabRow
import ru.topbun.minecraft_mods_pe.repository.ModRepository

object MainScreen: Screen {

    @Composable
    override fun Content() {
        val repository = ModRepository(LocalContext.current)
        val mods = repository.getMods()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.BLACK_BG)
                .statusBarsPadding()
                .padding(top = 24.dp, start = 20.dp, end = 20.dp)
        ) {
            TopBar()
            Spacer(Modifier.height(20.dp))
            SortBar()
            Spacer(Modifier.padding(vertical = 10.dp).fillMaxWidth().height(1.dp).background(Color(0xff464646)))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(vertical = 10.dp)
            ) {
                items(items = mods, key = {it.id}){
                    ModItem(it)
                }
            }
        }
    }

}



@Composable
private fun SortBar() {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val sortTypes = SortType.entries.map { stringResource(it.titleRes) }
    var selectedSortType by rememberSaveable { mutableStateOf(sortTypes.first()) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TabRow(
            modifier = Modifier.weight(1f),
            items = SortEnum.entries.map { stringResource(it.textRes) },
            selectedIndex = selectedTabIndex
        ) {
            selectedTabIndex = it
        }
        AppDropDown(
            value = selectedSortType,
            items = sortTypes
        ) {
            selectedSortType = it
        }
    }
}


@Composable
private fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        var text by rememberSaveable { mutableStateOf("") }
        AppTextField(
            modifier = Modifier.weight(1f),
            value = text,
            placeholder = stringResource(ru.topbun.minecraft_mods_pe.R.string.search),
            onValueChange = { text = it },
            iconStart = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "search",
                    tint = Colors.GRAY
                )
            }
        )
        Image(
            modifier = Modifier.size(28.dp),
            painter = painterResource(R.drawable.ic_mine_heart_filled),
            contentDescription = "favorite mods",
        )
    }
}