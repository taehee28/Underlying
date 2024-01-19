@file:OptIn(ExperimentalLayoutApi::class)

package com.thk.underlying.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.models.Emotion
import com.thk.underlying.models.Emotions
import com.thk.underlying.ui.components.dialog.EmotionPickerDialog
import com.thk.underlying.ui.theme.Pink300
import com.thk.underlying.ui.theme.Purple800
import com.thk.underlying.ui.theme.UnderlyingTheme
import com.thk.underlying.ui.theme.textButtonLarge

@Composable
fun IntroFirstQuestionText(
    enabled: Boolean,
    onEmotionSelected: (Emotion) -> Unit
) {
    var dialogVisible by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    QuestionStyleProvider {
        FlowRow {
            Text(text = "나는 요즘 ")
            QuestionTextButton(
                text = text,
                enabled = enabled
            ) {
                dialogVisible = true
            }
            // 통째로 줄바꿈 되는 것 방지
            Text(text = "을(를) ")
            Text(text = "느낀다.")
        }
    }

    if (dialogVisible) {
        EmotionPickerDialog(
            array = Emotions.abstractEmotions,
            onClick = {
                text = it.original
                onEmotionSelected(it)
            },
            onDismiss = { dialogVisible = false },
            columns = 2
        )
    }
}

@Composable
fun IntroSecondQuestionText(
    enabled: Boolean,
    onTextChange: (String) -> Unit
) {
    QuestionStyleProvider {
        Column {
            Text(text = "왜냐하면,")
            FlowRow {
                var text by remember { mutableStateOf("") }

                QuestionTextField(
                    text = text,
                    onTextChange = {
                        text = it
                        onTextChange(it.trim())
                    },
                    enabled = enabled
                )
                Text(text = " 때문이다.")
            }
        }
    }
}

@Composable
fun RepeatFirstQuestionText(
    enabled: Boolean,
    keyword: String,
    onTextChange: (String) -> Unit
) {
    QuestionStyleProvider {
        Column {
            Text(text = "왜 ${keyword}냐면,")
            FlowRow {
                var text by remember { mutableStateOf("") }

                QuestionTextField(
                    text = text,
                    onTextChange = {
                        text = it
                        onTextChange(it.trim())
                    },
                    enabled = enabled
                )
                Text(text = " 같기 때문이다.")
            }
        }
    }
}

@Composable
fun RepeatSecondQuestionText(
    enabled: Boolean,
    onEmotionSelected: (Emotion) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var dialogVisible by remember { mutableStateOf(false) }

    QuestionStyleProvider {
        FlowRow {
            Text(text = "그래서 나는 ")
            QuestionTextButton(
                text = text,
                enabled = enabled
            ) {
                dialogVisible = true
            }
            Text(text = "다.")
        }
    }

    if (dialogVisible) {
        EmotionPickerDialog(
            array = Emotions.detailEmotions,
            onClick = {
                text = it.statementEnding
                onEmotionSelected(it)
            },
            onDismiss = { dialogVisible = false }
        )
    }
}

@Composable
private fun QuestionStyleProvider(
    content: @Composable () -> Unit
) {
    ProvideTextStyle(value = MaterialTheme.typography.bodyLarge.copy(color = Color.White)) {
        content()
    }
}

@Composable
private fun QuestionTextButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit
) = Box {
    if (enabled) {
        UnderlineTextButton(
            text = text.ifEmpty { "어떤 감정" },
            color = if (text.isEmpty()) Pink300.copy(alpha = 0.5f) else Pink300,
            style = MaterialTheme.typography.textButtonLarge,
            onClick = onClick
        )
    } else {
        Text(text = text)
    }
}

@Composable
private fun QuestionTextField(
    text: String,
    onTextChange: (String) -> Unit,
    enabled: Boolean
) {
    if (enabled) {
        UnderlineTextField(
            text = text,
            onTextChange = onTextChange,
            hint = "어떤 이유",
            color = Pink300
        )
    } else {
        Text(text = text)
    }
}

@Preview
@Composable
private fun QuestionTextPreview() {
    UnderlyingTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(Purple800)
                .padding(16.dp)
        ) {
            IntroFirstQuestionText(true, {})
            IntroSecondQuestionText(enabled = true, onTextChange = {})
            RepeatFirstQuestionText(true, "어떤 이유 때문이", {})
            RepeatSecondQuestionText(enabled = true, onEmotionSelected = {})
        }
    }
}