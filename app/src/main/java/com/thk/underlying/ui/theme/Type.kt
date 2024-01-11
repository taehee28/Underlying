package com.thk.underlying.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.thk.underlying.R

val UnderlyingFont = FontFamily(
    Font(
        resId = R.font.pretendard,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.pretendard,
        weight = FontWeight.SemiBold
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    titleLarge = TextStyle(
        color = Gray950,
        fontFamily = UnderlyingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    bodyLarge = TextStyle(
        color = Gray950,
        fontFamily = UnderlyingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    bodyMedium = TextStyle(
        color = Gray950,
        fontFamily = UnderlyingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodySmall = TextStyle(
        color = Gray950,
        fontFamily = UnderlyingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        color = Gray950,
        fontFamily = UnderlyingFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

