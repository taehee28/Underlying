package com.thk.underlying.ui.navigation

sealed class Screen(val route: String) {
    data object OnBoarding : Screen("onBoarding")

    data object Home : Screen("home")

    data object Finding : Screen("finding") {
        data object InProgress : Screen("inProgress")
        data object Result : Screen("result")
        data object GoalSetting : Screen("goalSetting")
    }

    data object Guidance : Screen("guidance")
}