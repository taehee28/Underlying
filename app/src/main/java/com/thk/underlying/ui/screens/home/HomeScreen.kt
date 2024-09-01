package com.thk.underlying.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.R
import com.thk.underlying.models.GradientState
import com.thk.underlying.ui.components.GradientBackground
import com.thk.underlying.ui.theme.UnderlyingTheme
import com.thk.underlying.ui.theme.titleLargeSemiBold

@Composable
fun HomeScreen(
    navigateToFindingScreen: () -> Unit
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .widthIn(max = 600.dp)
            .fillMaxHeight()
            .padding(horizontal = 48.dp)
            .padding(top = 72.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Column {
                Text(
                    text = "감정의",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
                Text(
                    text = "알맹이",
                    style = MaterialTheme.typography.titleLargeSemiBold.copy(
                        color = Color.White,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
                Text(
                    text = "찾기",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    )
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = "start button",
                tint = Color.White,
                modifier = Modifier
                    .width(48.dp)
                    .fillMaxHeight()
                    .clickable(
                        onClick = { navigateToFindingScreen() },
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = null
                    )
            )
        }
    }
}

@Preview(name = "default display")
@Preview(device = "id:8in Foldable", name = "fold-out display")
@Preview(device = "id:6.7in Foldable", name = "fold-in display")
@Composable
fun HomeScreenPreview() {
    UnderlyingTheme {
        GradientBackground(stateProvider = { GradientState.DARK_TO_LIGHT }) {
            HomeScreen({})
        }
    }
}