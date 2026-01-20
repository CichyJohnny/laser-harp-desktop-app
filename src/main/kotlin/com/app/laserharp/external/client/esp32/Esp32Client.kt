package com.app.laserharp.external.client.esp32

import com.app.laserharp.domain.MusicTrack
import com.app.laserharp.external.client.model.MusicTrackRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fazecast.jSerialComm.SerialPort
import java.io.OutputStream
import java.nio.charset.StandardCharsets
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Component

@Component
class Esp32Client() : DisposableBean {

    private val objectMapper: ObjectMapper = jacksonObjectMapper()

    private val port: SerialPort =
        SerialPort.getCommPort(SERIAL_PORT_NAME).apply {
            baudRate = BAUD_RATE
            numDataBits = 8
            numStopBits = SerialPort.ONE_STOP_BIT
            parity = SerialPort.NO_PARITY
            setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, READ_TIMEOUT_MS, WRITE_TIMEOUT_MS)

            if (!openPort()) {
//                throw IllegalStateException("Failed to open serial port '$SERIAL_PORT_NAME'")
            }
            logger.info("Opened ESP32 serial port: {} at {} baud", systemPortName, baudRate)
        }

    private val out: OutputStream = port.outputStream

    fun sendMusicTrack(music: MusicTrack): String {
        val request = MusicTrackRequest.from(music)

        return try {
            val jsonLine = objectMapper.writeValueAsString(request) + "\n"
            val bytes = jsonLine.toByteArray(StandardCharsets.UTF_8)

            out.write(bytes)
            out.flush()

            logger.debug(
                "Successfully sent serial command for music track id: {} with notes {} ({} bytes)",
                music.id,
                music.notes,
                bytes.size,
            )
            "Command ${music.id} sent successfully!"
        } catch (e: Exception) {
            logger.error("Error sending serial command to ESP32", e)
            "Error sending command: ${e.message}"
        }
    }

    override fun destroy() {
        try {
            try {
                out.close()
            } catch (_: Exception) {}
            if (port.isOpen) port.closePort()
        } catch (_: Exception) {}
    }

    companion object {
        private val logger = LoggerFactory.getLogger(Esp32Client::class.java)

        private const val SERIAL_PORT_NAME = "COM3"
        private const val BAUD_RATE = 115200

        private const val READ_TIMEOUT_MS = 2000
        private const val WRITE_TIMEOUT_MS = 2000
    }
}
