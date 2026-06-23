package com.n64emu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.n64emu.model.RomEntry

@Composable
fun RomListPanel(
    roms: List<RomEntry>,
    selectedRom: RomEntry?,
    onSelectRom: (RomEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2D2D2D))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Text("Status", color = Color(0xFFCCCCCC), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(50.dp))
            Text("Rom Name", color = Color(0xFFCCCCCC), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("File Name", color = Color(0xFFCCCCCC), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(120.dp))
            Text("Size", color = Color(0xFFCCCCCC), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(60.dp))
            Text("Location", color = Color(0xFFCCCCCC), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.width(80.dp))
        }

        Divider(color = Color(0xFF3D3D3D), thickness = 1.dp)

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(roms) { rom ->
                val isSelected = rom == selectedRom
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(if (isSelected) Color(0xFF1A5276) else Color.Transparent)
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val statusIcon = if (rom.isFavorite) "★" else " "
                    Text(statusIcon, color = Color(0xFFFFD700), fontSize = 14.sp, modifier = Modifier.width(50.dp))
                    Text(rom.name, color = Color.White, fontSize = 13.sp, modifier = Modifier.weight(1f))
                    Text(rom.fileName, color = Color(0xFFAAAAAA), fontSize = 13.sp, modifier = Modifier.width(120.dp))
                    Text(rom.size, color = Color(0xFFAAAAAA), fontSize = 13.sp, modifier = Modifier.width(60.dp))
                    Text(rom.location, color = Color(0xFFAAAAAA), fontSize = 13.sp, modifier = Modifier.width(80.dp))
                }
            }
        }
    }
}
