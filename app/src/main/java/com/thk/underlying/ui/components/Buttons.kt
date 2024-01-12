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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.R
import com.thk.underlying.models.Emotions
import com.thk.underlying.ui.components.dialog.EmotionPickerDialog
import com.thk.underlying.ui.theme.ChipBackgroundColors
import com.thk.underlying.ui.theme.Gray950
import com.thk.underlying.ui.theme.Pink800
import com.thk.underlying.ui.theme.UnderlyingTheme

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
            .width(83.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.fixedSp)
        )
    }
}

/**
 * 밑줄 있는 텍스트 버튼.
 * 다른 일반 Text하고 정렬을 맞추려면 alignByBaseline을 전부 적용해야 함.
 */
@Composable
fun UnderlineTextButton(
    text: String,
    onClick: () -> Unit,
    hint: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .clickable(
                onClick = onClick,
                interactionSource = remember {
                    MutableInteractionSource()
                },
                indication = null
            )
            .then(modifier)
    ) {
        Text(
            text = text.ifEmpty { hint },
            style = MaterialTheme.typography.bodyLarge.copy(color = color),
            modifier = Modifier.alpha(if (text.isEmpty()) 0.5f else 1f)
        )
        Divider(
            thickness = 2.dp,
            color = color,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

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
                Text(text = "그래서 나는 ", modifier = Modifier.alignByBaseline())
                UnderlineTextButton(
                    text = text,
                    onClick =  { dialogVisible = true },
                    hint = "어떤 감정",
                    color = Pink800,
                    modifier = Modifier.alignByBaseline()
                )
                Text(text = "다.", modifier = Modifier.alignByBaseline())
            }

            if (dialogVisible) {
                EmotionPickerDialog(
                    array = Emotions.detailEmotions,
                    onClick = {
                        if (it != null) {
                            text = it.statementEnding
                        }
                        
                        dialogVisible = false
                    }
                )
            }
        }
    }
}