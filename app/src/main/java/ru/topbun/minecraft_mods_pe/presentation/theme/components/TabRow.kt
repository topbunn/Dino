package ru.topbun.minecraft_mods_pe.presentation.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography


@Composable
fun TabRow(
    items: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onChangeSelectedIndex: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(36.dp)
            .background(Color(0xffF3F3F3), RoundedCornerShape(8.dp))
            .padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items.forEachIndexed { index, item ->
            val background = if (selectedIndex == index) MaterialTheme.colorScheme.primary else Color.Transparent
            val textColor = if (selectedIndex == index) Colors.WHITE else Colors.GREEN
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = background)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onChangeSelectedIndex(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = item,
                    style = Typography.APP_TEXT,
                    fontSize = 15.sp,
                    color = textColor,
                    fontFamily = Fonts.SF.MEDIUM,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}