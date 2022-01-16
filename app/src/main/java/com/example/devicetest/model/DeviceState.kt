package com.example.devicetest.model

import androidx.annotation.DrawableRes
import com.example.devicetest.R

data class DeviceState(
    val currentMode: DeviceMode = DeviceMode.OFF,
    val availableDeviceMods: List<DeviceMode> = listOf(
        DeviceMode.OFF,
        DeviceMode.AUTO,
        DeviceMode.HEAT,
        DeviceMode.COLD,
        DeviceMode.FAN,
        DeviceMode.DRY
    ),
    val progMode: DeviceProgMode = DeviceProgMode.BASIC,
    val availableProgMods: List<DeviceProgMode> = listOf(DeviceProgMode.BASIC, DeviceProgMode.PROG),
    val currentTemperature: String = "20.2",
    val pendingTemperature: String = "21.0",
    val absenceTime: String = "00:00",
    @DrawableRes val topIcon: Int = R.drawable.ic_hourgrass,
    @DrawableRes val bottomIcon: Int = R.drawable.ic_mode_dry,
    @DrawableRes val background: Int = R.drawable.background_circle_auto,
)