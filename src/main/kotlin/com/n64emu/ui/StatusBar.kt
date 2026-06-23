package com.n64emu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.n64emu.model.PluginsState

@Composable
fun StatusBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF1E1E1E))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            val statusColor = when {
                PluginsState.isPaused -> Color(0xFFFFA726)
                PluginsState.currentRom.isNotEmpty() -> Color(0xFF66BB6A)
                else -> Color(0xFF888888)
            }
            val statusText = when {
                PluginsState.isPaused -> "Paused"
                PluginsState.currentRom.isNotEmpty() -> "Running"
                else -> PluginsState.systemStatus
            }
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(statusColor, shape = MaterialTheme.shapes.extraLarge)
            )
            Spacer(Modifier.width(6.dp))
            Text(statusText, color = Color(0xFFAAAAAA), fontSize = 11.sp)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            if (PluginsState.currentRom.isNotEmpty()) {
                Text(
                    "ROM: ${PluginsState.currentRom}",
                    color = Color(0xFFAAAAAA),
                    fontSize = 11.sp
                )
            }
            Text(
                "FPS: ${PluginsState.fps}",
                color = Color(0xFFAAAAAA),
                fontSize = 11.sp
            )
        }
    }
}
