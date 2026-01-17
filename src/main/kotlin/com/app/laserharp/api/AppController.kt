package com.app.laserharp.api

import com.app.laserharp.domain.MusicTrackService
import com.app.laserharp.external.client.esp32.Esp32Client
import com.app.laserharp.external.template.ModelProcessor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AppController(
    private val esp32Client: Esp32Client,
    private val modelProcessor: ModelProcessor,
    private val musicTrackService: MusicTrackService,
) {
    @GetMapping("/")
    fun home(model: Model): String {
        modelProcessor.initializeMainPage(model)

        return "main_page"
    }

    @PostMapping("/api/play")
    @ResponseBody
    fun playMusic(@RequestParam id: Int): String {
        logger.debug("Received request on /api/play endpoint for musicId {}", id)
        val music = musicTrackService.findMusicTrackById(id)
        return esp32Client.sendMusicTrack(music)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(AppController::class.java)
    }
}
