@file:OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)

package com.thk.underlying.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.models.Emotions
import com.thk.underlying.ui.components.dialog.EmotionPickerDialog
import com.thk.underlying.ui.theme.Pink800
import com.thk.underlying.ui.theme.UnderlyingTheme



@Composable
fun UnderlineTextField(
    text: String,
    onTextChange: (String) -> Unit,
    hint: String,
    color: Color
) {
    Box(
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        val _text by rememberUpdatedState(newValue = text)

        val hintVisible by remember {
            derivedStateOf { _text.isEmpty() }
        }

        if (hintVisible) {
            Text(
                text = hint,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = color,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.alpha(0.5f)
            )
        }

        BasicTextField(
            value = _text,
            onValueChange = { onTextChange(it) },
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = color,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun UnderlineTextFieldPreview() {
    UnderlyingTheme {
        // 텍스트 입력 용 예시
        FlowRow(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            var text by remember {
                mutableStateOf("")
            }

            UnderlineTextField(
                text = text,
                onTextChange = { text = it },
                hint = "어떤 이유",
                color = Pink800
            )
            Text(
                text = " 때문이다."
            )
        }
    }
}