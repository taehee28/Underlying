package com.thk.underlying.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.thk.underlying.ui.theme.Gray950
import com.thk.underlying.ui.theme.UnderlyingTheme

@Composable
fun SimpleDialog(
    content: String,
    onDismiss: () -> Unit,
    onConfirmClick: (() -> Unit)? = null,
    onCancelClick: (() -> Unit)? = null
) = Dialog(onDismissRequest = onDismiss) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .widthIn(max = 400.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = content,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                lineHeight = 22.sp,
                modifier = Modifier.padding(top = 50.dp, bottom = 30.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                SimpleButton(
                    text = "취소",
                    onClick = { onConfirmClick?.invoke() },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
                Divider(
                    color = Gray950,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .alpha(0.4f)
                        .padding(vertical = 16.dp)
                )
                SimpleButton(
                    text = "확인",
                    onClick = { onCancelClick?.invoke() },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                )
            }
        }
    }
}

@Composable
private fun SimpleButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier.clickable(
        onClick = onClick,
        interactionSource = remember {
            MutableInteractionSource()
        }, 
        indication = null
    )
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall
    )
}

@Preview(name = "default display")
@Preview(device = "id:8in Foldable", name = "fold-out display")
@Preview(device = "id:6.7in Foldable", name = "fold-in display")
@Composable
fun SimpleDialogPreview() {
    UnderlyingTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            SimpleDialog(
                content = "진행한 내용이 사라집니다.\n그만하시겠습니까?",
                onDismiss = {}
            )
        }
    }
}