package com.app.laserharp.external.client.esp32

import com.app.laserharp.domain.MusicTrack
import com.app.laserharp.external.client.model.MusicTrackRequest
import com.app.laserharp.external.storage.MusicTrackRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class Esp32Client(private val musicTrackRepository: MusicTrackRepository) {
    private val restTemplate = RestTemplate()

    fun sendMusicTrack(id: Int): String {
        val music = findMusicTrackById(id)
        val request = MusicTrackRequest.from(music)

        try {
            val url = buildEspUrl()
            restTemplate.postForObject(url, request, String::class.java).also {
                logger.debug(
                    "Successfully sent command to ESP32 for music track id: {} with notes {}",
                    music.id,
                    music.notes
                )
            }
            return "Command $id sent successfully!"
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error sending command: ${e.message}"
        }
    }

    private fun findMusicTrackById(id: Int): MusicTrack = musicTrackRepository.findById(id).get().toDomain()

    private fun buildEspUrl() = "$ESP32_IP/play"

    companion object {
        private const val ESP32_IP = "http://192.168.1.105"

        private val logger = LoggerFactory.getLogger(Esp32Client::class.java)
    }
}
