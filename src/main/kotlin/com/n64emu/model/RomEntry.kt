package com.n64emu.model

data class RomEntry(
    val name: String,
    val fileName: String,
    val size: String,
    val location: String,
    val isFavorite: Boolean = false
)
