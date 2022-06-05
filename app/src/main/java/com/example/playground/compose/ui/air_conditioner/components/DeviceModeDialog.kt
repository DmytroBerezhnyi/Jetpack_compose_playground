package com.example.playground.compose.ui.air_conditioner.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.playground.compose.ui.air_conditioner.model.DeviceMode
import com.example.playground.compose.theme.Gray

@ExperimentalComposeUiApi
@Composable
fun DeviceModeDialog(
    modifier: Modifier = Modifier,
    availableDeviceMods: List<DeviceMode>,
    currentMode: DeviceMode,
    onModeSelected: (DeviceMode) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {

        var selectedMode by remember { mutableStateOf(currentMode) }

        Column(modifier.background(Color.White)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "You can change mode blah blah, blah, blah blah blah, blah, blah",
                fontSize = 20.sp,
                color = Gray
            )

            ModesList(
                modifier = Modifier,
                availableDeviceMods = availableDeviceMods,
                currentMode = selectedMode,
                onModeSelected = { mode ->
                    selectedMode = mode
                }
            )

            Button(
                modifier = Modifier
                    .size(200.dp, 80.dp)
                    .padding(16.dp)
                    .align(CenterHorizontally),
                onClick = {
                    onModeSelected(selectedMode)
                }
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "Select",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ModesList(
    modifier: Modifier,
    availableDeviceMods: List<DeviceMode>,
    currentMode: DeviceMode,
    onModeSelected: (DeviceMode) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        availableDeviceMods.forEachIndexed { index, deviceMode ->
            ModeItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                mode = deviceMode,
                isSelected = availableDeviceMods.indexOf(currentMode) == index,
                onModeSelected = onModeSelected
            )
        }
    }
}

@Composable
fun ModeItem(
    modifier: Modifier = Modifier,
    mode: DeviceMode,
    isSelected: Boolean,
    onModeSelected: (DeviceMode) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Log.d("Tag", "Here $mode")

        RadioButton(
            selected = isSelected,
            onClick = { onModeSelected.invoke(mode) }
        )
        Text(
            modifier = Modifier.padding(end = 16.dp),
            text = mode.name
        )
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun DeviceModeDialogPreview() {
    DeviceModeDialog(
        modifier = Modifier.width(400.dp),
        availableDeviceMods = DeviceMode.values().toList(),
        currentMode = DeviceMode.AUTO,
        onModeSelected = {},
        onDismissRequest = {},
    )
}