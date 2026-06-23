package com.n64emu.ui.settings

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
import com.n64emu.model.PluginInfo
import com.n64emu.model.PluginsState

@Composable
fun PluginConfigDialog(
    title: String,
    plugin: PluginInfo,
    pluginType: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFF2D2D2D),
            modifier = Modifier.width(500.dp).height(400.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Divider(color = Color(0xFF3D3D3D))
                Spacer(Modifier.height(12.dp))

                Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                    Text("About $title", color = Color(0xFFCCCCCC), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))

                    PluginInfoRow("Name:", plugin.name.ifEmpty { "$pluginType Default Plugin" })
                    PluginInfoRow("Author:", plugin.author.ifEmpty { "Unknown" })
                    PluginInfoRow("Version:", plugin.version.ifEmpty { "1.0" })
                    PluginInfoRow("Path:", plugin.path.ifEmpty { "Not loaded" })

                    Spacer(Modifier.height(16.dp))
                    Divider(color = Color(0xFF3D3D3D))
                    Spacer(Modifier.height(12.dp))

                    Text("Configuration", color = Color(0xFFCCCCCC), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text("No configuration options available in this beta version.", color = Color(0xFF888888), fontSize = 13.sp)
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
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E5FF))
                    ) {
                        Text("OK", fontWeight = FontWeight.Bold, color = Color.Black)
                    }
                }
            }
        }
    }
}

@Composable
private fun PluginInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = Color(0xFFAAAAAA), fontSize = 13.sp, modifier = Modifier.width(80.dp))
        Text(value, color = Color.White, fontSize = 13.sp)
    }
}
