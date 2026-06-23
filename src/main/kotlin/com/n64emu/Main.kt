package com.n64emu

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.n64emu.ui.N64EmuWindow

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "N64Emu Beta",
        state = WindowState(size = DpSize(800.dp, 600.dp)),
        resizable = true
    ) {
        N64EmuWindow()
    }
}
