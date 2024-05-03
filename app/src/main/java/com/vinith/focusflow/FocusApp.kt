package com.vinith.focusflow

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FocusApp() {
    val isAppBlockEnabled = remember { mutableStateOf(false) }
    val isPomodoroEnabled = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Stopwatch UI
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "00",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp) // Add padding to separate from buttons
            ) // Hours
            Text(
                text = "00",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp) // Add padding to separate from buttons
            ) // Minutes
            Text(
                text = "00",
                fontSize = 72.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ) // Seconds
        }

        // Toggle Buttons and Menu Icon
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomToggleButton(text = "App Block") {
                    isAppBlockEnabled.value = !isAppBlockEnabled.value
                }
                CustomToggleButton(text = "Pomodoro") {
                    isPomodoroEnabled.value = !isPomodoroEnabled.value
                }
            }
        }
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Insights Menu",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 16.dp) // Adjust padding as needed
        )
    }
}
