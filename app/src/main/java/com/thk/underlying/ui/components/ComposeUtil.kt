package com.thk.underlying.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val Int.fixedSp: TextUnit
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp

fun Modifier.noRippleClickable(
    enabled: Boolean = true,
    onClick: () -> Unit
): Modifier = composed {
    this.clickable(
        onClick = onClick,
        enabled = enabled,
        interactionSource = remember {
            MutableInteractionSource()
        },
        indication = null
    )
}