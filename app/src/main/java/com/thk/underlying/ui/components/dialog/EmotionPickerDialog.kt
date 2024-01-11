package com.thk.underlying.ui.components.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.thk.underlying.ui.components.ChipButton
import com.thk.underlying.ui.components.CloseButton

val sampleStrings = arrayOf(
    "불안",
    "두려움",
    "무서움",
    "지침",
    "힘듦",
    "막막함",
    "외면",
    "피하다",
    "부담",
    "긴장",
    "초조함",
    "싫음",
    "별로",
    "불편함",
    "불쾌함",
    "속상함",
    "서운",
    "슬픔"
)

@Composable
fun EmotionPickerDialog(
    onButtonClick: (String?) -> Unit
) {
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(82.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalArrangement = Arrangement.spacedBy(40.dp),
                contentPadding = PaddingValues(vertical = 10.dp)
            ) {
                items(sampleStrings) { string ->
                    ChipButton(text = string) {
                        onButtonClick(string)
                    }
                }
            }

            Spacer(modifier = Modifier.height(50.dp))

            CloseButton {
                onButtonClick(null)
            }
        }
    }
}

@Preview
@Composable
fun EmotionPickerDialogPreview() {
    Box(
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

        if (visible) {
            EmotionPickerDialog {
                visible = false
            }
        }
    }
}