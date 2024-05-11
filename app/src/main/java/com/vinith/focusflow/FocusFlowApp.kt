package com.vinith.focusflow


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*

@Composable
fun FocusFlowApp() {
    val isAppBlockEnabled = remember { mutableStateOf(false) }
    val isRunning = remember { mutableStateOf(false) }
    val timeState = remember { mutableStateOf(0L) }
    val coroutineScope = rememberCoroutineScope()
    val timerJob = remember { mutableStateOf<Job?>(null) }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "FocusFlow",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 16.dp, top = 38.dp)
            )
            // Insights Menu Icon
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Insights Menu",
                modifier = Modifier
                    .padding(end = 16.dp, top = 38.dp) // Adjust padding as needed
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Stopwatch UI
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val hours = timeState.value / 3600
                val minutes = (timeState.value % 3600) / 60
                val seconds = timeState.value % 60

                Text(
                    text = String.format("%02d", hours),
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp) // Add padding to separate from buttons
                ) // Hours
                Text(
                    text = String.format("%02d", minutes),
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp) // Add padding to separate from buttons
                ) // Minutes
                Text(
                    text = String.format("%02d", seconds),
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ) // Seconds
            }

            // Start/Stop Button
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Start/Stop Button
                    Text(
                        text = if (isRunning.value) "Stop" else "Start",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .clickable {
                                if (isRunning.value) {
                                    timerJob.value?.cancel()
                                } else {
                                    timeState.value = 0L // Reset time when starting
                                    timerJob.value = startTimer(coroutineScope, timeState)
                                }
                                isRunning.value = !isRunning.value
                            }
                    )
                    // App Block Switch
                    CustomSwitch(
                        text = "App Block",
                        isChecked = isAppBlockEnabled.value
                    ) {
                        isAppBlockEnabled.value = it
                    }
                }
            }
        }
    }
}


private fun startTimer(scope: CoroutineScope, timeState: MutableState<Long>): Job {
    return scope.launch {
        var time = 0L
        while (true) {
            delay(1000)
            time++
            timeState.value = time
        }
    }
}
