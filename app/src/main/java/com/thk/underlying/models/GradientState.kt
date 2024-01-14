package com.thk.underlying.models

import androidx.compose.ui.graphics.Color
import com.thk.underlying.ui.theme.Purple300
import com.thk.underlying.ui.theme.Purple800

enum class GradientState(
    val start: Color,
    val center: Color,
    val end: Color
) {
    FULL_DARK(Purple800, Purple800, Purple800),
    DARK_TO_LIGHT(Purple800, Purple800, Purple300),
    LIGHT_TO_DARK(Purple300, Purple300, Purple800),
    FULL_LIGHT(Purple300, Purple300, Purple300)
}