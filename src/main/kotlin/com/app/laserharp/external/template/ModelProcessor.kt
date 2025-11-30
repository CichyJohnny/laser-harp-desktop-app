package com.app.laserharp.external.template

import com.app.laserharp.external.storage.MusicTrackRepository
import org.springframework.stereotype.Component
import org.springframework.ui.Model

@Component
class ModelProcessor(private val musicTrackRepository: MusicTrackRepository) {
    fun initializeMainPage(model: Model) {
        val playlist = musicTrackRepository.findAll().map { it.toDomain() }

        model.addAttribute("tracks", playlist)
    }
}
