package ru.topbun.feedback

import android.R.attr.name
import android.R.id.message
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.topbun.feedback.FeedbackState.FeedbackScreenState
import ru.topbun.ui.R
import ru.topbun.ui.components.AppButton
import ru.topbun.ui.components.AppTextField
import ru.topbun.ui.components.NativeAd
import ru.topbun.ui.theme.Colors
import ru.topbun.ui.theme.Fonts
import ru.topbun.ui.theme.Typography

object FeedbackScreen: Tab, Screen {

    override val options: TabOptions
        @Composable get() = TabOptions(
            2U,
            stringResource(R.string.tabs_feedback),
            painterResource(R.drawable.ic_tabs_feedback)
        )

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(Colors.BLACK_BG)
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp, top = 50.dp)
        ) {
            val context = LocalContext.current
            val viewModel = viewModel<FeedbackViewModel>()
            val state by viewModel.state.collectAsState()
            val messageSent = stringResource(R.string.message_is_sent)

            LaunchedEffect(state.feedbackState) {
                when(val feedbackState = state.feedbackState){
                     is FeedbackScreenState.Error -> {
                        Toast.makeText(context, feedbackState.message, Toast.LENGTH_SHORT).show()
                    }
                    FeedbackScreenState.Success -> {
                        Toast.makeText(context, messageSent, Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }

            Text(
                modifier = Modifier.Companion.fillMaxWidth(),
                text = stringResource(R.string.contact_us),
                style = Typography.APP_TEXT,
                fontSize = 28.sp,
                color = Colors.GRAY,
                textAlign = TextAlign.Companion.Center,
                fontFamily = Fonts.SF.BOLD,
            )
            Spacer(Modifier.Companion.height(50.dp))
            Text(
                modifier = Modifier.Companion.fillMaxWidth(),
                text = stringResource(R.string.any_question),
                style = Typography.APP_TEXT,
                fontSize = 24.sp,
                color = Colors.GRAY,
                textAlign = TextAlign.Companion.Center,
                fontFamily = Fonts.SF.SEMI_BOLD,
            )
            Spacer(Modifier.Companion.height(30.dp))
            AppTextField(
                value = state.email,
                placeholder = stringResource(R.string.email),
                onValueChange = { viewModel.changeEmail(it) }
            )
            Spacer(Modifier.Companion.height(10.dp))
            AppTextField(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(150.dp),
                alignment = Alignment.Companion.Top,
                singleLine = false,
                value = state.message,
                placeholder = stringResource(R.string.type_message),
                onValueChange = { if (it.length < 1024) viewModel.changeMessage(it) }
            )
            Spacer(Modifier.Companion.height(20.dp))
            val buttonEnabled by viewModel.buttonEnabled.collectAsState()
            AppButton(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = buttonEnabled,
                loading = state.feedbackState is FeedbackScreenState.Loading,
                text = stringResource(R.string.send)
            ) {
                viewModel.sendIssue()
            }
            Spacer(Modifier.Companion.height(20.dp))
            NativeAd(context)
        }
    }

}