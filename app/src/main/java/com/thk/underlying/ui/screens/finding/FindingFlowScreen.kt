package com.thk.underlying.ui.screens.finding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thk.underlying.R
import com.thk.underlying.models.GradientState
import com.thk.underlying.ui.components.GradientBackground
import com.thk.underlying.ui.components.NavigationButtonRow
import com.thk.underlying.ui.components.noRippleClickable
import com.thk.underlying.ui.theme.UnderlyingTheme

@Composable
fun FindingFlowScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
            .padding(horizontal = 16.dp)
    ) {
        NavigationButtonRow(
            prevEnable = { true },
            onPrevClick = { /*TODO*/ },
            nextEnable = { true },
            onNextClick = {}
        )
    }
}

@Preview(name = "default display")
@Preview(device = "id:8in Foldable", name = "fold-out display")
@Preview(device = "id:6.7in Foldable", name = "fold-in display")
@Composable
fun FindingFlowScreenPreview() {
    UnderlyingTheme {
        GradientBackground(stateProvider = { GradientState.FULL_DARK }) {
            FindingFlowScreen()
        }
    }
}