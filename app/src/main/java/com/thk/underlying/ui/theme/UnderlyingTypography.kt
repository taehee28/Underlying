package com.thk.underlying.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.thk.underlying.R

val UnderlyingFont = FontFamily(
    Font(
        resId = R.font.pretendard_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.pretendard_semibold,
        weight = FontWeight.SemiBold
    )
)

object UnderlyingTypography {
    val textButtonLarge: TextStyle = TextStyle(
        color = Gray950,
        fontFamily = UnderlyingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        textDecoration = TextDecoration.Underline
    )
    val textButtonSmall: TextStyle = TextStyle(
        color = Gray950,
        fontFamily = UnderlyingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textDecoration = TextDecoration.Underline
    )
}
