package com.n64emu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AboutDialog(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFF2D2D2D),
            modifier = Modifier.width(380.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    "N64Emu",
                    color = Color(0xFF00E5FF),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Version 1.0.0-beta",
                    color = Color(0xFF888888),
                    fontSize = 14.sp
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    "Nintendo 64 Emulator",
                    color = Color(0xFFAAAAAA),
                    fontSize = 13.sp
                )
                Spacer(Modifier.height(16.dp))
                Divider(color = Color(0xFF3D3D3D))
                Spacer(Modifier.height(16.dp))
                Text("Built with Kotlin & Compose Desktop", color = Color(0xFF666666), fontSize = 11.sp)
                Spacer(Modifier.height(8.dp))
                Text("Beta version - UI prototype", color = Color(0xFF666666), fontSize = 11.sp)
                Spacer(Modifier.height(20.dp))
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF)),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("OK", fontWeight = FontWeight.Bold, color = Color.Black)
                }
            }
        }
    }
}
