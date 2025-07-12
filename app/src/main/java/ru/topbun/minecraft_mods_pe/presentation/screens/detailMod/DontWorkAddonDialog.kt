package ru.topbun.minecraft_mods_pe.presentation.screens.detailMod

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.topbun.minecraft_mods_pe.presentation.theme.Colors
import ru.topbun.minecraft_mods_pe.presentation.theme.Fonts
import ru.topbun.minecraft_mods_pe.presentation.theme.Typography
import ru.topbun.minecraft_mods_pe.presentation.theme.components.AppButton
import ru.topbun.minecraft_mods_pe.presentation.theme.components.AppTextField
import ru.topbun.minecraft_mods_pe.presentation.theme.components.DialogWrapper

@Composable
fun DontWorkAddonDialog(
    onDismissDialog: () -> Unit,
) {
    DialogWrapper(onDismissDialog) {
        var context = LocalContext.current
        var message by rememberSaveable { mutableStateOf("") }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(ru.topbun.domain.R.string.describe_your_problem),
            style = Typography.APP_TEXT,
            fontSize = 16.sp,
            color = Colors.GRAY,
            fontFamily = Fonts.SF.MEDIUM,
        )
        Spacer(Modifier.height(16.dp))
        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            alignment = Alignment.Top,
            singleLine = false,
            value = message,
            background = Colors.BLACK_BG,
            placeholder = stringResource(ru.topbun.domain.R.string.type_message),
            onValueChange = {message = it}
        )
        Spacer(Modifier.height(16.dp))

        val messageIsSent = stringResource(ru.topbun.domain.R.string.message_is_sent)
        AppButton(
            text = stringResource(ru.topbun.domain.R.string.send),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Toast.makeText(context, messageIsSent, Toast.LENGTH_SHORT).show()
            onDismissDialog()
        }
    }
}