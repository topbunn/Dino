package ru.topbun.detail_mod

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.topbun.android.utills.copyAssetToDownloads
import ru.topbun.android.utills.getModFile
import ru.topbun.ui.theme.Colors
import ru.topbun.ui.theme.Fonts
import ru.topbun.ui.theme.Typography
import ru.topbun.ui.components.AppButton
import ru.topbun.ui.components.DialogWrapper
import ru.topbun.ui.R
import java.io.File

@Composable
fun SetupModDialog(
    assetFilePath: String,
    viewModel: DetailModViewModel,
    onDismissDialog: () -> Unit,
) {
    val context = LocalContext.current
    val activity = LocalActivity.currentOrThrow
    var savedFile by rememberSaveable { mutableStateOf<File?>(null) }
    LaunchedEffect(assetFilePath) { savedFile = getModFile(assetFilePath) }
    DialogWrapper(
        onDismissDialog = onDismissDialog,
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(
                if (savedFile != null) R.string.to_install_in_game_click_the_install else R.string.to_save_click_the_download_button
            ),
            style = Typography.APP_TEXT,
            fontSize = 16.sp,
            color = Colors.GRAY,
            fontFamily = Fonts.SF.MEDIUM,
        )
        Spacer(Modifier.height(16.dp))
        AppButton(
            text = stringResource(
                if (savedFile != null) R.string.install else R.string.download
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            savedFile?.let {
                viewModel.installMod(context, it)
            } ?: run {
                savedFile = copyAssetToDownloads(context, assetFilePath)
                viewModel.showInAppReview(activity)
            }
        }
        savedFile?.let {
            Spacer(Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = buildAnnotatedString {
                    append(stringResource(R.string.the_file_is_saved_the_path))
                    withStyle(SpanStyle(color = Colors.BUTTON_RED)) {
                        append(it.path)
                    }
                },
                style = Typography.APP_TEXT,
                fontSize = 16.sp,
                color = Colors.GRAY,
                fontFamily = Fonts.SF.MEDIUM,
            )
        }
    }
}