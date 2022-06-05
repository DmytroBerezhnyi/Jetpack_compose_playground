package com.example.playground.compose.ui.air_conditioner.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.playground.compose.ui.air_conditioner.model.DeviceProgMode
import com.example.playground.compose.theme.Gray

@Composable
fun DeviceProgMods(
    modifier: Modifier,
    deviceProgMode: DeviceProgMode,
    availableProgMods: List<DeviceProgMode>,
    onSelectedProgMode: (DeviceProgMode) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        availableProgMods.forEachIndexed { index, mod ->
            Text(
                modifier = Modifier.clickable { onSelectedProgMode(availableProgMods[index]) },
                text = mod.name,
                fontSize = 36.sp,
                color = if (deviceProgMode == availableProgMods[index]) Color.Black else Gray,
                textDecoration = if (deviceProgMode == availableProgMods[index]) TextDecoration.Underline else TextDecoration.None
            )
        }
    }
}