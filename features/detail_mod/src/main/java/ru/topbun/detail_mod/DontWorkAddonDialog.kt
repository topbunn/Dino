package ru.topbun.detail_mod

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
import ru.topbun.ui.theme.Colors
import ru.topbun.ui.theme.Fonts
import ru.topbun.ui.theme.Typography
import ru.topbun.ui.components.AppButton
import ru.topbun.ui.components.AppTextField
import ru.topbun.ui.components.DialogWrapper
import ru.topbun.ui.R

@Composable
fun DontWorkAddonDialog(
    onDismissDialog: () -> Unit,
) {
    DialogWrapper(onDismissDialog) {
        var context = LocalContext.current
        var message by rememberSaveable { mutableStateOf("") }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.describe_your_problem),
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
            placeholder = stringResource(R.string.type_message),
            onValueChange = {message = it}
        )
        Spacer(Modifier.height(16.dp))

        val messageIsSent = stringResource(R.string.message_is_sent)
        AppButton(
            text = stringResource(R.string.send),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Toast.makeText(context, messageIsSent, Toast.LENGTH_SHORT).show()
            onDismissDialog()
        }
    }
}