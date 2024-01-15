package com.thk.underlying.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    onEmotionSelected: (Emotion) -> Unit
) {
    var dialogVisible by remember { mutableStateOf(false) }

    Row {
        var text by remember { mutableStateOf("") }

        Text(
            text = "나는 요즘 ",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )
        UnderlineTextButton(
            text = text.ifEmpty { "어떤 감정" },
            color = if (text.isEmpty()) Pink300 else Pink300.copy(alpha = 0.5f),
            style = MaterialTheme.typography.textButtonLarge,
            onClick = { dialogVisible = true }
        )
        Text(
            text = "을 느낀다.",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White)
        )
    }

    if (dialogVisible) {
        EmotionPickerDialog(
            array = Emotions.abstractEmotions,
            onClick = onEmotionSelected,
            onDismiss = { dialogVisible = false }
        )
    }
}

@Preview
@Composable
private fun QuestionTextPreview() {
    UnderlyingTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            IntroFirstQuestionText({})
        }
    }
}