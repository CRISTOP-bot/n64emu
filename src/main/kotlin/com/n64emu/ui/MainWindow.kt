package com.n64emu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import com.n64emu.model.PluginsState
import com.n64emu.model.RomEntry
import com.n64emu.ui.settings.PluginConfigDialog
import com.n64emu.ui.settings.SettingsDialog

@Composable
fun FrameWindowScope.N64EmuWindow() {
    var showSettings by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }
    var showGraphicsConfig by remember { mutableStateOf(false) }
    var showAudioConfig by remember { mutableStateOf(false) }
    var showControllerConfig by remember { mutableStateOf(false) }
    var showRspConfig by remember { mutableStateOf(false) }

    val sampleRoms = remember {
        listOf(
            RomEntry("Super Mario 64", "sm64.n64", "8 MB", "C:\\Roms", true),
            RomEntry("The Legend of Zelda: OoT", "zelda_oot.n64", "32 MB", "C:\\Roms"),
            RomEntry("Mario Kart 64", "mk64.n64", "16 MB", "C:\\Roms"),
            RomEntry("GoldenEye 007", "goldeneye.n64", "16 MB", "C:\\Roms"),
            RomEntry("Star Fox 64", "sfox64.n64", "12 MB", "C:\\Roms"),
            RomEntry("Banjo-Kazooie", "banjo.n64", "16 MB", "C:\\Roms"),
            RomEntry("Perfect Dark", "pdark.n64", "32 MB", "C:\\Roms"),
            RomEntry("Paper Mario", "paper_mario.n64", "40 MB", "C:\\Roms"),
            RomEntry("Donkey Kong 64", "dk64.n64", "32 MB", "C:\\Roms"),
            RomEntry("Majora's Mask", "mmask.n64", "32 MB", "C:\\Roms"),
        )
    }

    var selectedRom by remember { mutableStateOf<RomEntry?>(null) }

    MenuBar {
        Menu("File", mnemonic = 'F') {
            Item("Choose ROM...", onClick = { })
            Item("Refresh ROM List", onClick = { })
            Separator()
            Item("Play ROM", onClick = { selectedRom?.let { PluginsState.currentRom = it.name } })
            Item("End Emulation", onClick = { PluginsState.currentRom = "" })
            Separator()
            Item("Exit", onClick = { })
        }
        Menu("System", mnemonic = 'S') {
            Item("Reset", onClick = { })
            Item("Pause", onClick = { PluginsState.isPaused = !PluginsState.isPaused })
            Separator()
            Item("Full Screen", onClick = { })
        }
        Menu("Options", mnemonic = 'O') {
            Item("Settings...", onClick = { showSettings = true })
            Separator()
            Item("Configure Graphics Plugin...", onClick = { showGraphicsConfig = true })
            Item("Configure Audio Plugin...", onClick = { showAudioConfig = true })
            Item("Configure Controller Plugin...", onClick = { showControllerConfig = true })
            Item("Configure RSP Plugin...", onClick = { showRspConfig = true })
        }
        Menu("Debug", mnemonic = 'D') {
            Item("Generate Trace", onClick = { })
            Item("Dump Registers", onClick = { })
            Item("Dump Memory", onClick = { })
            Separator()
            Item("Debugger", onClick = { })
        }
        Menu("Help", mnemonic = 'H') {
            Item("About...", onClick = { showAbout = true })
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF1E1E1E))) {
        Toolbar(
            onPlay = { selectedRom?.let { PluginsState.currentRom = it.name } },
            onPause = { PluginsState.isPaused = !PluginsState.isPaused },
            onReset = { PluginsState.isPaused = false; PluginsState.currentRom = "" }
        )

        RomListPanel(
            roms = sampleRoms,
            selectedRom = selectedRom,
            onSelectRom = { selectedRom = it },
            modifier = Modifier.weight(1f)
        )

        StatusBar()
    }

    if (showSettings) {
        SettingsDialog(onDismiss = { showSettings = false })
    }
    if (showAbout) {
        AboutDialog(onDismiss = { showAbout = false })
    }
    if (showGraphicsConfig) {
        PluginConfigDialog(
            title = "Configure Graphics Plugin",
            plugin = PluginsState.graphicsPlugin,
            pluginType = "Graphics",
            onDismiss = { showGraphicsConfig = false }
        )
    }
    if (showAudioConfig) {
        PluginConfigDialog(
            title = "Configure Audio Plugin",
            plugin = PluginsState.audioPlugin,
            pluginType = "Audio",
            onDismiss = { showAudioConfig = false }
        )
    }
    if (showControllerConfig) {
        PluginConfigDialog(
            title = "Configure Controller Plugin",
            plugin = PluginsState.controllerPlugin,
            pluginType = "Controller",
            onDismiss = { showControllerConfig = false }
        )
    }
    if (showRspConfig) {
        PluginConfigDialog(
            title = "Configure RSP Plugin",
            plugin = PluginsState.rspPlugin,
            pluginType = "RSP",
            onDismiss = { showRspConfig = false }
        )
    }
}

@Composable
private fun Toolbar(
    onPlay: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF252525))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ToolbarButton("▶", Color(0xFF66BB6A), onClick = onPlay)
        ToolbarButton("⏸", Color(0xFFFFA726), onClick = onPause)
        ToolbarButton("⏹", Color(0xFFEF5350), onClick = onReset)
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(Color(0xFF3D3D3D))
        )
        Spacer(Modifier.width(8.dp))
        ToolbarButton("⟳", Color(0xFF888888), onClick = onReset)
        Spacer(Modifier.weight(1f))
        Text("N64Emu Beta", color = Color(0xFF444444), fontSize = 11.sp)
    }
}

@Composable
private fun ToolbarButton(
    icon: String,
    color: Color,
    onClick: () -> Unit
) {
    Text(
        text = icon,
        color = color,
        fontSize = 18.sp,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}
