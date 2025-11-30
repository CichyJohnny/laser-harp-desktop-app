package com.app.laserharp.api

import com.app.laserharp.external.client.esp32.Esp32Client
import com.app.laserharp.external.template.ModelProcessor
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class AppController(private val esp32Client: Esp32Client, private val modelProcessor: ModelProcessor) {
    @GetMapping("/")
    fun home(model: Model): String {
        modelProcessor.initializeMainPage(model)

        return "main_page"
    }

    @PostMapping("/api/play")
    @ResponseBody
    fun playMusic(@RequestParam id: Int): String {
        return esp32Client.sendMusicTrack(id)
    }
}
