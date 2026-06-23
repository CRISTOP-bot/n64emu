package com.n64emu.model

data class PluginInfo(
    val name: String,
    val author: String,
    val version: String,
    val path: String
)

object PluginsState {
    var graphicsPlugin: PluginInfo = PluginInfo("", "", "", "")
    var audioPlugin: PluginInfo = PluginInfo("", "", "", "")
    var controllerPlugin: PluginInfo = PluginInfo("", "", "", "")
    var rspPlugin: PluginInfo = PluginInfo("", "", "", "")

    var systemStatus: String = "Ready"
    var currentRom: String = ""
    var fps: Int = 0
    var isPaused: Boolean = false
}
