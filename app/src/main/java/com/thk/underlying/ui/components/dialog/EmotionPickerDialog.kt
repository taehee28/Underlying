package com.thk.underlying.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.thk.underlying.models.Emotion
import com.thk.underlying.models.Emotions
import com.thk.underlying.ui.components.ChipButton
import com.thk.underlying.ui.components.CloseButton

/**
 * [Emotions.abstractEmotions]를 사용하는 경우에는 columns를 2로 주어야 함
 */
@Composable
fun EmotionPickerDialog(
    array: Array<Emotion>,
    onClick: (Emotion) -> Unit,
    onDismiss: () -> Unit,
    columns: Int = 3
) = Dialog(
    onDismissRequest = onDismiss
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(40.dp),
            contentPadding = PaddingValues(vertical = 10.dp),
            modifier = Modifier.widthIn(max = 400.dp)
        ) {
            items(array) { emotion ->
                ChipButton(text = emotion.original) {
                    onClick(emotion)
                    onDismiss()
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        CloseButton {
            onDismiss()
        }
    }
}

@Preview(name = "default display")
@Preview(device = "id:8in Foldable", name = "fold-out display")
@Preview(device = "id:6.7in Foldable", name = "fold-in display")
@Composable
fun EmotionPickerDialogPreview() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var visible by remember {
            mutableStateOf(false)
        }

        Button(onClick = { visible = true }) {
            Text(text = "다이얼로그")
        }

        if (true) {
            EmotionPickerDialog(
                array = Emotions.detailEmotions,
                onClick = {},
                onDismiss = { visible = false }
            )
        }
    }
}
