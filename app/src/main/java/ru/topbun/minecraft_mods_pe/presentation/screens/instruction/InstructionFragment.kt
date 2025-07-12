package ru.topbun.minecraft_mods_pe.presentation.screens.instruction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.topbun.domain.R
import ru.topbun.minecraft_mods_pe.presentation.screens.instruction.InstructionType.*
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography
import ru.topbun.minecraft_mods_pe.presentation.theme.components.noRippleClickable

class InstructionFragment(private val type: InstructionType): Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.GRAY_BG)
                .navigationBarsPadding()
                .statusBarsPadding()
                .background(Colors.BLACK_BG)
        ) {
            Header(type)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val instructions = when(type){
                    ADDON -> InstructionEntity.getAddonInstruction()
                    WORLD -> InstructionEntity.getWorldInstruction()
                }
                instructions.forEachIndexed { index, item ->
                    InstructionItem(
                        title = "${index + 1} " + stringResource(item.titleRes),
                        image = painterResource(item.imageRes)
                    )
                }
            }
        }
    }
}

@Composable
private fun InstructionItem(title: String, image: Painter) {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
        Text(
            text = title,
            style = Typography.APP_TEXT,
            fontSize = 18.sp,
            color = Colors.GRAY,
            fontFamily = Fonts.SF.SEMI_BOLD,
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = image,
            contentDescription = title,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun Header(type: InstructionType) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.GRAY_BG)
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Icon(
            modifier = Modifier
                .height(20.dp)
                .noRippleClickable { navigator.pop() },
            painter = painterResource(R.drawable.ic_back),
            contentDescription = "button back",
            tint = Colors.GREEN
        )
        val titleRes = when(type){
            ADDON -> ru.topbun.domain.R.string.installing_addons_and_textures
            WORLD -> ru.topbun.domain.R.string.installation_of_worlds
        }
        Text(
            text = stringResource(titleRes),
            style = Typography.APP_TEXT,
            fontSize = 18.sp,
            color = Colors.GRAY,
            fontFamily = Fonts.SF.BOLD,
        )
        Box{}
    }
}