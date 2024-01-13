package com.thk.underlying.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.R
import com.thk.underlying.models.Emotions
import com.thk.underlying.ui.components.dialog.EmotionPickerDialog
import com.thk.underlying.ui.theme.ChipBackgroundColors
import com.thk.underlying.ui.theme.Gray950
import com.thk.underlying.ui.theme.Pink800
import com.thk.underlying.ui.theme.UnderlyingTheme
import com.thk.underlying.ui.theme.textButtonLarge
import com.thk.underlying.ui.theme.textButtonSmall

@Composable
fun CloseButton(onClick: () -> Unit) = Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
        containerColor = Color.White,
        contentColor = Gray950
    ),
    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
    shape = CircleShape,
    contentPadding = PaddingValues(8.dp),
    modifier = Modifier
        .defaultMinSize(1.dp, 1.dp)
        .size(50.dp)
) {
    Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "close button")
}

@Composable
fun ChipButton(
    text: String,
    onClick: () -> Unit
) = Box(contentAlignment = Alignment.Center) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ChipBackgroundColors.random(),
            contentColor = Gray950
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(30.dp),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        modifier = Modifier
            .defaultMinSize(1.dp, 1.dp)
            .widthIn(min = 83.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.fixedSp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun UnderlineTextButton(
    text: String,
    color: Color,
    style: TextStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Text(
    text = text,
    style = style.copy(color = color),
    modifier = modifier.clickable(
        onClick = onClick,
        interactionSource = remember {
            MutableInteractionSource()
        },
        indication = null
    )
)

@Preview
@Composable
fun CloseButtonPreview() {
    Box(
        Modifier
            .background(color = Color.White)
            .padding(50.dp)) {
        CloseButton {}
    }
}

@Preview
@Composable
fun ChipButtonPreview() {
    ChipButton(text = "속상함") {

    }
}

@Preview
@Composable
fun UnderlineTextButtonPreview() {
    UnderlyingTheme {
        Box {
            var text by remember {
                mutableStateOf("")
            }
            var dialogVisible by remember {
                mutableStateOf(false)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = "그래서 나는 ")
                UnderlineTextButton(
                    text = text.ifEmpty { "어떤 감정" },
                    color = if (text.isEmpty()) Pink800.copy(alpha = 0.5f) else Pink800,
                    style = MaterialTheme.typography.textButtonLarge,
                    onClick = { dialogVisible = true }
                )
                Text(text = "다.")
            }

            if (dialogVisible) {
                EmotionPickerDialog(
                    array = Emotions.detailEmotions,
                    onClick = {
                        text = it.statementEnding
                    },
                    onDismiss = {
                        dialogVisible = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun UnderlineTextButtonSmallPreview() {
    UnderlyingTheme {
        Column {
            UnderlineTextButton(
                text = "그만하기",
                color = Color.White,
                style = MaterialTheme.typography.textButtonSmall,
                onClick = { /*TODO*/ }
            )
            UnderlineTextButton(
                text = "메인으로 돌아가기",
                color = Color.White,
                style = MaterialTheme.typography.textButtonSmall,
                onClick = { /*TODO*/ }
            )
        }
    }
}