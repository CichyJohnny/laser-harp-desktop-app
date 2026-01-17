package com.app.laserharp.domain

interface MusicTrackRepository {
    fun findById(id: Int): MusicTrack

    fun findAll(): List<MusicTrack>

    fun save(track: MusicTrack): MusicTrack

    fun saveAll(tracks: List<MusicTrack>): List<MusicTrack>

    fun count(): Long
}
