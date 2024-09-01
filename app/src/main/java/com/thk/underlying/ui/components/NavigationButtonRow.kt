package com.thk.underlying.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.thk.underlying.R

@Composable
fun NavigationButtonRow(
    prevEnable: () -> Boolean,
    onPrevClick: () -> Unit,
    nextEnable: () -> Boolean,
    onNextClick: () -> Unit
) = Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier.fillMaxWidth()
) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        contentDescription = "previous button",
        tint = if (prevEnable()) Color.White else Color.White.copy(alpha = 0f),
        modifier = Modifier
            .size(32.dp)
            .noRippleClickable(
                enabled = prevEnable(),
                onClick = onPrevClick
            )
    )
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_forward),
        contentDescription = "next button",
        tint = if (nextEnable()) Color.White else Color.White.copy(alpha = 0.3f),
        modifier = Modifier
            .size(32.dp)
            .noRippleClickable(
                enabled = nextEnable(),
                onClick = onNextClick
            )
    )
}