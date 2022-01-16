package com.example.devicetest.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.devicetest.R
import com.example.devicetest.animation.AnimateVisibilityFromBottomToTop
import com.example.devicetest.animation.AnimateVisibilityFromTopToBottom
import com.example.devicetest.model.DeviceState
import com.example.devicetest.ui.theme.Gray

@ExperimentalAnimationApi
@Composable
fun DeviceCircle(
    modifier: Modifier,
    width: Dp,
    deviceState: DeviceState
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isInEditMode by remember { mutableStateOf(false) }

        TopArrow(visible = isInEditMode)

        Box(modifier = Modifier.size(width, width)) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                painter = painterResource(id = deviceState.background),
                contentDescription = ""
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopContent(modifier = Modifier, deviceState = deviceState)
                CenterContent(
                    modifier = Modifier.clickable {
                        isInEditMode = isInEditMode.not()
                    },
                    deviceState = deviceState
                )
                BottomContent(
                    modifier = Modifier,
                    deviceState = deviceState
                )
            }
        }
        BottomArrow(visible = isInEditMode)
    }
}

@Composable
fun BottomArrow(visible: Boolean) {
    Column(modifier = Modifier.size(70.dp, 70.dp)) {
        AnimateVisibilityFromTopToBottom(visible = visible) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun TopArrow(visible: Boolean) {
    Column(modifier = Modifier.size(70.dp, 70.dp)) {
        AnimateVisibilityFromBottomToTop(visible = visible) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_arrow_up),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun CenterContent(modifier: Modifier, deviceState: DeviceState) {
    Text(
        modifier = modifier,
        text = deviceState.currentTemperature,
        fontSize = 100.sp,
        color = Gray
    )
}

@Composable
fun TopContent(modifier: Modifier, deviceState: DeviceState) {
    Row(
        modifier = modifier
            .padding(top = 34.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(36.dp)
                .padding(end = 4.dp),
            painter = painterResource(id = deviceState.topIcon),
            contentDescription = ""
        )
        Text(
            text = deviceState.absenceTime,
            color = Gray,
            fontSize = 36.sp
        )
    }
}

@Composable
fun BottomContent(modifier: Modifier, deviceState: DeviceState) {
    Row(
        modifier = modifier
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = deviceState.pendingTemperature,
            fontSize = 36.sp,
            color = Gray
        )
        Image(
            modifier = Modifier
                .size(36.dp)
                .padding(4.dp),
            painter = painterResource(id = deviceState.bottomIcon),
            contentDescription = ""
        )
    }
}