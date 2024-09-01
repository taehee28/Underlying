package com.thk.underlying.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.thk.underlying.models.GradientState
import com.thk.underlying.ui.components.GradientBackground
import com.thk.underlying.ui.navigation.Screen
import com.thk.underlying.ui.screens.finding.FindingFlowScreen
import com.thk.underlying.ui.screens.finding.FindingFlowViewModel
import com.thk.underlying.ui.screens.home.HomeScreen
import com.thk.underlying.ui.theme.UnderlyingTheme
import com.thk.underlying.utils.logd

@Composable
fun UnderlyingApp() {
    UnderlyingTheme {
        val navController = rememberNavController()

        var backgroundState by remember {
            mutableStateOf(GradientState.DARK_TO_LIGHT)
        }

        GradientBackground(stateProvider = { backgroundState }) {
            UnderlyingNavHost(navController = navController)
        }


        DisposableEffect(navController) {
            val callback = NavController.OnDestinationChangedListener { _, destination, _ ->
                logd(">> new destination = $destination")

                backgroundState = when (destination.route) {
                    Screen.Home.route -> GradientState.DARK_TO_LIGHT
                    Screen.Finding.InFlow.route -> GradientState.FULL_DARK
                    Screen.Finding.Result.route -> GradientState.LIGHT_TO_DARK
                    Screen.Finding.GoalSetting.route -> GradientState.FULL_LIGHT
                    else -> GradientState.DARK_TO_LIGHT
                }
            }

            navController.addOnDestinationChangedListener(callback)

            onDispose {
                navController.removeOnDestinationChangedListener(callback)
            }
        }
    }
}

@Composable
private fun UnderlyingNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                navigateToFindingScreen = { navController.navigate(route = Screen.Finding.route) }
            )
        }

        navigation(startDestination = Screen.Finding.InFlow.route, route = Screen.Finding.route) {
            composable(route = Screen.Finding.InFlow.route) {
                val viewModel: FindingFlowViewModel = viewModel()

                FindingFlowScreen(
                    visibleList = viewModel.inputList,
                    moveToNext = viewModel::moveToNext,
                    moveToPrev = viewModel::moveToPrev,
                    updateInput = viewModel::updateInput,
                    moveToHomeScreen = {
                        navController.popBackStack(route = Screen.Home.route, inclusive = false)
                    }
                )
            }
        }
    }
}