package com.example.playground.compose.ui.air_conditioner

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.playground.R
import com.example.playground.compose.ui.air_conditioner.components.DeviceCircle
import com.example.playground.compose.ui.air_conditioner.components.DeviceModeDialog
import com.example.playground.compose.ui.air_conditioner.components.DeviceProgMods
import com.example.playground.compose.theme.Background

@Composable
fun AirConditioner(navController: NavHostController) {
    val viewModel: AirConditionerViewModel = viewModel()

    var isDialogShown by remember { mutableStateOf(false) }

    val airConditionerState by viewModel.liveDeviceState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Image(
            modifier = Modifier
                .size(60.dp, 60.dp)
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 16.dp)
                .clickable {
                    isDialogShown = true
                },
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = ""
        )

        DeviceCircle(
            modifier = Modifier
                .align(Alignment.Center),
            width = 400.dp,
            deviceState = airConditionerState
        )

        DeviceProgMods(
            modifier = Modifier.align(Alignment.BottomCenter),
            deviceProgMode = airConditionerState.progMode,
            availableProgMods = airConditionerState.availableProgMods,
            onSelectedProgMode = {
                viewModel.updateData(airConditionerState.copy(progMode = it))
            }
        )

        if (isDialogShown) {
            DeviceModeDialog(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.Center),
                availableDeviceMods = airConditionerState.availableDeviceMods,
                currentMode = airConditionerState.currentMode,
                onModeSelected = {
                    viewModel.updateData(airConditionerState.copy(currentMode = it))
                    isDialogShown = false
                },
                onDismissRequest = {
                    isDialogShown = false
                }
            )
        }
    }
}