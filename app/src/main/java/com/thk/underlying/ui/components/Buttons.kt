package com.thk.underlying.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.R
import com.thk.underlying.ui.theme.ChipBackgroundColors
import com.thk.underlying.ui.theme.Gray950

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
    modifier = Modifier.defaultMinSize(1.dp, 1.dp)
) {
    Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "close button")
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

@Composable
fun ChipButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ChipBackgroundColors.random(),
            contentColor = Gray950
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(30.dp),
        contentPadding = PaddingValues(vertical = 12.dp, horizontal = 16.dp),
        modifier = Modifier.defaultMinSize(1.dp, 1.dp).width(82.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.fixedSp)
        )
    }
}

@Preview
@Composable
fun ChipButtonPreview() {
    ChipButton(text = "속상함") {

    }
}