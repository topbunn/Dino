package ru.topbun.minecraft_mods_pe.presentation.screens.detailMod

import android.os.Parcelable
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.parcelize.Parcelize
import ru.topbun.domain.R
import ru.topbun.domain.entity.ModEntity
import ru.topbun.minecraft_mods_pe.presentation.screens.instruction.InstructionScreen
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography
import ru.topbun.minecraft_mods_pe.presentation.theme.components.AppButton
import ru.topbun.minecraft_mods_pe.presentation.theme.components.IconWithButton
import ru.topbun.minecraft_mods_pe.presentation.theme.components.NativeAd
import ru.topbun.minecraft_mods_pe.presentation.theme.components.noRippleClickable
import ru.topbun.minecraft_mods_pe.utills.getImageWithNameFile

@Parcelize
data class DetailModScreen(private val mod: ModEntity) : Screen, Parcelable {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        val viewModel = remember { DetailModViewModel(context, mod) }
        val state by viewModel.state.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Colors.GRAY_BG)
                .navigationBarsPadding()
                .statusBarsPadding()
                .background(Colors.BLACK_BG)
        ) {
            Header(viewModel, state)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ) {
                ButtonInstruction(navigator)
                Spacer(Modifier.height(10.dp))
                Preview(state.mod)
                Spacer(Modifier.height(20.dp))
                TitleWithDescr(state.mod)
                Spacer(Modifier.height(10.dp))
                Metrics(state.mod)
                Spacer(Modifier.height(20.dp))
                SupportVersions(state)
                Spacer(Modifier.height(20.dp))
                NativeAd(context)
                Spacer(Modifier.height(20.dp))
                FileButtons(viewModel, state)
            }
        }
        state.choiceFilePathSetup?.let {
            SetupModDialog(it, viewModel::installMod) {
                viewModel.changeStageSetupMod(null)
            }
        }
        if (state.dontWorkAddonDialogIsOpen){
            DontWorkAddonDialog { viewModel.openDontWorkDialog(false) }
        }
    }

}

@Composable
private fun MinecraftVersion(state: DetailModState) {
    state.versionMine?.let {
        Spacer(Modifier.height(20.dp))
        Text(
            text = "Minecraft version: $it",
            style = Typography.APP_TEXT,
            fontSize = 18.sp,
            color = Colors.WHITE,
            fontFamily = Fonts.SF.SEMI_BOLD,
        )
    }
}

@Composable
private fun FileButtons(viewModel: DetailModViewModel, state: DetailModState) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        state.mod.files.forEach {
            AppButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                text = it,
                contentColor = Color(0xff4AD858),
                containerColor = Colors.GREEN_BG,
            ) {
                viewModel.changeStageSetupMod(it)
            }
        }
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            text = stringResource(ru.topbun.domain.R.string.addon_don_t_work),
            contentColor = Colors.WHITE,
            containerColor = Color(0xffE03131),
        ) {
            viewModel.openDontWorkDialog(true)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SupportVersions(state: DetailModState) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(ru.topbun.domain.R.string.supported_versions),
            style = Typography.APP_TEXT,
            fontSize = 18.sp,
            color = Colors.WHITE,
            fontFamily = Fonts.SF.SEMI_BOLD,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            state.mod.supportVersion.forEach { version ->
                SupportVersionItem(
                    value = version,
                    actualVersion = state.versionMine?.let { it.contains(version) } ?: false
                )
            }
        }
    }
    MinecraftVersion(state)
}

@Composable
private fun SupportVersionItem(value: String, actualVersion: Boolean = false) {
    Text(
        modifier = Modifier
            .background(if (actualVersion) Colors.GREEN else Colors.WHITE, RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        text = value,
        style = Typography.APP_TEXT,
        fontSize = 15.sp,
        color = Colors.BLACK_BG,
        fontFamily = Fonts.SF.SEMI_BOLD,
    )
}

@Composable
private fun Metrics(mod: ModEntity) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        IconWithButton(mod.countDownload.toString(), R.drawable.ic_download)
        IconWithButton(mod.countFavorite.toString(), R.drawable.ic_favorite)
    }
}

@Composable
private fun TitleWithDescr(mod: ModEntity) {
    Text(
        text = mod.title,
        style = Typography.APP_TEXT,
        fontSize = 24.sp,
        color = Colors.WHITE,
        fontFamily = Fonts.SF.BOLD,
    )
    Spacer(Modifier.height(10.dp))
    Text(
        text = mod.description,
        style = Typography.APP_TEXT,
        fontSize = 14.sp,
        color = Colors.GRAY,
        fontFamily = Fonts.SF.MEDIUM,
    )
}

@Composable
private fun Preview(mod: ModEntity) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        painter = painterResource(getImageWithNameFile(mod.previewRes)),
        contentDescription = mod.title,
        contentScale = ContentScale.FillWidth
    )
}

@Composable
private fun ButtonInstruction(navigator: Navigator) {
    AppButton(
        text = stringResource(ru.topbun.domain.R.string.instructions),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        navigator.push(InstructionScreen)
    }
}

@Composable
private fun Header(viewModel: DetailModViewModel, state: DetailModState) {
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
        Text(
            text = stringResource(ru.topbun.domain.R.string.installation),
            style = Typography.APP_TEXT,
            fontSize = 18.sp,
            color = Colors.GRAY,
            fontFamily = Fonts.SF.BOLD,
        )
        Image(
            modifier = Modifier
                .size(24.dp)
                .noRippleClickable { viewModel.changeFavorite() },
            painter = painterResource(
                if (state.mod.isFavorite) R.drawable.ic_mine_heart_filled else R.drawable.ic_mine_heart_stroke
            ),
            contentDescription = "favorite mods",
        )
    }
}