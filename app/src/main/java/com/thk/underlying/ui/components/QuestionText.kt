package com.thk.underlying.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.models.Emotion
import com.thk.underlying.models.Emotions
import com.thk.underlying.ui.components.dialog.EmotionPickerDialog
import com.thk.underlying.ui.theme.Pink300
import com.thk.underlying.ui.theme.UnderlyingTheme
import com.thk.underlying.ui.theme.textButtonLarge

@Composable
fun IntroFirstQuestionText(
    enabled: Boolean,
    onEmotionSelected: (Emotion) -> Unit
) {
    var dialogVisible by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

    QuestionRow {
        Text(text = "나는 요즘 ")
        QuestionTextButton(
            text = text,
            hint = "어떤 감정",
            enabled = enabled
        ) {
            dialogVisible = true
        }
        Text(text = "을 느낀다.")
    }

    if (dialogVisible) {
        EmotionPickerDialog(
            array = Emotions.abstractEmotions,
            onClick = {
                text = it.original
                onEmotionSelected(it)
            },
            onDismiss = { dialogVisible = false }
        )
    }
}

@Composable
private fun QuestionRow(
    content: @Composable RowScope.() -> Unit
) {
    ProvideTextStyle(value = MaterialTheme.typography.bodyLarge.copy(color = Color.White)) {
        Row(content = content)
    }
}

@Composable
private fun QuestionTextButton(
    text: String,
    hint: String,
    enabled: Boolean,
    onClick: () -> Unit
) = Box {
    if (enabled) {
        UnderlineTextButton(
            text = text.ifEmpty { hint },
            color = if (text.isEmpty()) Pink300.copy(alpha = 0.5f) else Pink300,
            style = MaterialTheme.typography.textButtonLarge,
            onClick = onClick
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            IntroFirstQuestionText(true, {})
        }
    }
}