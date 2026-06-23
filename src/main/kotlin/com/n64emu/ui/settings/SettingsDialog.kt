package com.n64emu.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
fun SettingsDialog(onDismiss: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Game Settings", "ROM Selection", "Directory Settings", "Advanced")

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFF2D2D2D),
            modifier = Modifier.width(600.dp).height(450.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Settings", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Divider(color = Color(0xFF3D3D3D))
                Spacer(Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.width(140.dp)) {
                        tabs.forEachIndexed { index, tab ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        if (selectedTab == index) Color(0xFF1A5276)
                                        else Color.Transparent,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    tab,
                                    color = if (selectedTab == index) Color.White else Color(0xFFAAAAAA),
                                    fontSize = 13.sp,
                                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }

                    Spacer(Modifier.width(16.dp))
                    Divider(
                        color = Color(0xFF3D3D3D),
                        modifier = Modifier.fillMaxHeight().width(1.dp)
                    )
                    Spacer(Modifier.width(16.dp))

                    Column(
                        modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
                    ) {
                        when (selectedTab) {
                            0 -> GameSettingsTab()
                            1 -> RomSelectionTab()
                            2 -> DirectorySettingsTab()
                            3 -> AdvancedTab()
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))
                Divider(color = Color(0xFF3D3D3D))
                Spacer(Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onDismiss,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF)),
                        modifier = Modifier.width(80.dp)
                    ) {
                        Text("OK", fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                    Spacer(Modifier.width(8.dp))
                    OutlinedButton(
                        onClick = onDismiss,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF888888))
                    ) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}

@Composable
private fun GameSettingsTab() {
    Column {
        SettingsCheckbox("Clear Frame Buffer", true)
        SettingsCheckbox("Update Frame Screen", true)
        SettingsCheckbox("Self Texture", false)
        Spacer(Modifier.height(12.dp))
        SettingsDropdown("Default Save Type:", "First Save Type", listOf("First Save Type", "4kbit EEPROM", "16kbit EEPROM", "SRAM", "Flash RAM"))
        Spacer(Modifier.height(8.dp))
        SettingsDropdown("Counter Factor:", "2", listOf("1", "2", "3", "4", "5", "6"))
    }
}

@Composable
private fun RomSelectionTab() {
    Column {
        SettingsCheckbox("Use Defaults Based On ROM File", false)
        Spacer(Modifier.height(8.dp))
        SettingsCheckbox("Use Defaults Based On Country", false)
        Spacer(Modifier.height(8.dp))
        SettingsCheckbox("Use Defaults Based On CRC", true)
    }
}

@Composable
private fun DirectorySettingsTab() {
    Column {
        SettingsField("ROM Directory:", "C:\\N64\\Roms")
        Spacer(Modifier.height(8.dp))
        SettingsField("Plugin Directory:", "C:\\N64Emu\\Plugins")
        Spacer(Modifier.height(8.dp))
        SettingsField("Save Directory:", "C:\\N64Emu\\Saves")
        Spacer(Modifier.height(8.dp))
        SettingsField("Screenshot Directory:", "C:\\N64Emu\\Screenshots")
    }
}

@Composable
private fun AdvancedTab() {
    Column {
        SettingsCheckbox("Compile Blocks", true)
        Spacer(Modifier.height(6.dp))
        SettingsCheckbox("Skip BIOS", false)
        Spacer(Modifier.height(6.dp))
        SettingsCheckbox("Show FPS", true)
        Spacer(Modifier.height(6.dp))
        SettingsCheckbox("Full Screen", false)
        Spacer(Modifier.height(12.dp))
        SettingsSlider("CPU Core", 1f, 0f, 10f)
    }
}

@Composable
private fun SettingsCheckbox(label: String, checked: Boolean) {
    var state by remember { mutableStateOf(checked) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = state,
            onCheckedChange = { state = it },
            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF00E5FF))
        )
        Text(label, color = Color(0xFFCCCCCC), fontSize = 13.sp)
    }
}

@Composable
private fun SettingsDropdown(label: String, default: String, options: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(default) }

    Column {
        Text(label, color = Color(0xFFCCCCCC), fontSize = 12.sp)
        Spacer(Modifier.height(4.dp))
        Box {
            OutlinedButton(
                onClick = { expanded = true },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(selected, modifier = Modifier.weight(1f))
                Text("▼", fontSize = 10.sp)
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { opt ->
                    DropdownMenuItem(
                        text = { Text(opt) },
                        onClick = { selected = opt; expanded = false }
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsField(label: String, value: String) {
    Column {
        Text(label, color = Color(0xFFCCCCCC), fontSize = 12.sp)
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFF00E5FF),
                unfocusedBorderColor = Color(0xFF555555)
            )
        )
    }
}

@Composable
private fun SettingsSlider(label: String, value: Float, min: Float, max: Float) {
    var sliderValue by remember { mutableStateOf(value) }
    Column {
        Text(label, color = Color(0xFFCCCCCC), fontSize = 12.sp)
        Spacer(Modifier.height(4.dp))
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = min..max,
            colors = SliderDefaults.colors(
                thumbColor = Color(0xFF00E5FF),
                activeTrackColor = Color(0xFF00E5FF)
            )
        )
    }
}
