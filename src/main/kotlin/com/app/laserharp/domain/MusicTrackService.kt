package com.app.laserharp.domain

import org.springframework.stereotype.Service

@Service
class MusicTrackService(private val musicTrackRepository: MusicTrackRepository) {
    fun findMusicTrackById(id: Int): MusicTrack = musicTrackRepository.findById(id)
}
