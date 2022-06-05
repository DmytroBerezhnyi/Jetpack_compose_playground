package com.example.playground.compose

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playground.compose.ui.air_conditioner.AirConditioner

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "AirConditioner"
    ) {
        composable("AirConditioner") {
            AirConditioner(navController)
        }
    }
}