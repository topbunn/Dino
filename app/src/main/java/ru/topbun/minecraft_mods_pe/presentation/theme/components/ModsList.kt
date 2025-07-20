package ru.topbun.minecraft_mods_pe.presentation.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.topbun.domain.entity.ModEntity
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography

@Composable
fun ColumnScope.ModsList(mods: List<ModEntity>, content: @Composable (() -> Unit)? = null, onClickFavorite: (ModEntity) -> Unit, onClickMod: (ModEntity) -> Unit) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        content?.let { item { content() } }
        if (mods.isNotEmpty()){
            mods.forEachIndexed { index, mod ->
                item{
                    ModItem(
                        mod = mod,
                        onClickFavorite = { onClickFavorite(mod) },
                        onClickMod = { onClickMod(mod) }
                    )
                }
                if (index != 0 && ((index + 1) % 2 == 0)){
                    item {
                        NativeAd(context)
                    }
                }
            }
        } else {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(ru.topbun.ui.R.string.the_list_is_empty),
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