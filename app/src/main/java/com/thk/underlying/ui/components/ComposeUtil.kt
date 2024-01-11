package com.thk.underlying.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

val Int.fixedSp: TextUnit
    @Composable
    get() = (this / LocalDensity.current.fontScale).sp