package com.example.devicetest.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.devicetest.R
import com.example.devicetest.model.DeviceState
import com.example.devicetest.ui.theme.Background

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun AirConditioner() {
    var airConditionerState by remember { mutableStateOf(DeviceState()) }
    var isDialogShown by remember { mutableStateOf(false) }

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
                airConditionerState = airConditionerState.copy(progMode = it)
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
                    airConditionerState = airConditionerState.copy(currentMode = it)
                    isDialogShown = false
                },
                onDismissRequest = {
                    isDialogShown = false
                }
            )
        }
    }
}