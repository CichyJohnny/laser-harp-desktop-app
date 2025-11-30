package com.app.laserharp.domain

data class MusicTrack(
    val id: Int,
    val title: String,
    val artist: String,
    val imageUrl: String,
    val audioUrl: String,
    val sheetMusicUrl: String,
    val notes: List<String>,
)
