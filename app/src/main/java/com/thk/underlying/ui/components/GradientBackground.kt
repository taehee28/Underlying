package com.thk.underlying.ui.components

import android.util.Log
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.thk.underlying.models.GradientState
import com.thk.underlying.ui.theme.Purple800
import com.thk.underlying.ui.theme.UnderlyingTheme

@Composable
fun GradientBackground(
    stateProvider: () -> GradientState,
    content: @Composable BoxScope.() -> Unit
) {
    val backgroundState by rememberUpdatedState(newValue = stateProvider())
    val trans = updateTransition(targetState = backgroundState, "background color transition")

    val start by trans.animateColor(
        label = "start color",
        transitionSpec = {
            when {
                GradientState.FULL_LIGHT isTransitioningTo GradientState.FULL_DARK ||
                    GradientState.LIGHT_TO_DARK isTransitioningTo GradientState.FULL_DARK ||
                    GradientState.DARK_TO_LIGHT isTransitioningTo GradientState.FULL_LIGHT-> {
                    tween(1000)
                }
                else -> {
                    tween(1000, easing = LinearOutSlowInEasing)

                }
            }
        }
    ) {
        it.start
    }
    val center by trans.animateColor(
        label = "center color",
        transitionSpec = {
            when {
                GradientState.FULL_DARK isTransitioningTo GradientState.FULL_LIGHT ||
                    GradientState.FULL_LIGHT isTransitioningTo GradientState.FULL_DARK ||
                    GradientState.LIGHT_TO_DARK isTransitioningTo GradientState.FULL_DARK ||
                    GradientState.DARK_TO_LIGHT isTransitioningTo GradientState.FULL_LIGHT -> {
                    tween(1000, easing = LinearOutSlowInEasing)
                }
                else -> {
                    tween(1000)
                }
            }
        }
    ) {
        it.center
    }
    val end by trans.animateColor(
        label = "end color",
        transitionSpec = {
            when {
                GradientState.FULL_DARK isTransitioningTo GradientState.FULL_LIGHT  -> {
                    tween(1000)
                }
                else -> {
                    tween(1000, easing = LinearOutSlowInEasing)

                }
            }
        }
    ) {
        it.end
    }

    val colors = arrayOf(
        0f to start,
        0.52f to center,
        1f to end
    )

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colorStops = colors)),
        content = content
    )
}


@Preview
@Composable
fun GradientBackgroundPreview() {
    UnderlyingTheme {
        var backgroundState by remember {
            mutableStateOf(GradientState.DARK_TO_LIGHT)
        }

        GradientBackground(
            stateProvider = { backgroundState }
        ) {
            Button(
                onClick = {
                    backgroundState = GradientState.entries.random()
                },
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "바꾸기")
            }
        }
    }
}