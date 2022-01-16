package com.example.devicetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.devicetest.screen.air_conditioner.AirConditioner
import com.example.devicetest.ui.theme.DeviceTestTheme

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeviceTestTheme {
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
        }
    }
}