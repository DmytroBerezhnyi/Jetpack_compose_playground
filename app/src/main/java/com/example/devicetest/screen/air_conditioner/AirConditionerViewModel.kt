package com.example.devicetest.screen.air_conditioner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devicetest.model.DeviceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AirConditionerViewModel @Inject constructor() : ViewModel() {

    val currentState = MutableStateFlow(DeviceState())

    val liveDeviceState = flow {
        val random = Random(System.currentTimeMillis())
        while (true) {
            delay(800)
            emit(
                DeviceState().copy(
                    currentTemperature = roundOffDecimal(random.nextDouble() * 20.0 + 10.0).toString(),
                    pendingTemperature = roundOffDecimal(random.nextDouble() * 25.0 + 5.0).toString()
                )
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DeviceState()
    )

    private fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }

    fun updateData(deviceState: DeviceState) {
        viewModelScope.launch {
            // airConditioner.
            currentState.value = deviceState
        }
    }
}