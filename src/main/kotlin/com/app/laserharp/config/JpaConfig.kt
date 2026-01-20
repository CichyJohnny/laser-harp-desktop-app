package com.app.laserharp.config

import com.app.laserharp.domain.MusicTrack
import com.app.laserharp.domain.MusicTrackRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JpaConfig {
    @Bean
    fun initDatabase(repository: MusicTrackRepository) = CommandLineRunner {
        if (repository.count() == 0L) {
            val musicTracks =
                listOf(
                    MusicTrack(
                        1,
                        "Wlazł kotek na płotek",
                        "Oskar Kolberg",
                        "/img/kotek_cover.png",
                        "/audio/kotek.mp3",
                        "/img/kotek_sheet.jpg",
                        listOf("G", "E", "E", "F", "D", "D", "C", "E", "G", "G", "E", "E", "F", "D", "D", "C", "E", "C"),
                    ),
                    MusicTrack(
                        2,
                        "Oda do Radości",
                        "Friedrich Schiller",
                        "/img/oda_cover.png",
                        "/audio/oda.mp3",
                        "/img/oda_sheet.jpg",
                        listOf("E", "E", "F", "G", "G", "F", "E", "D"),
                    ),
                    MusicTrack(
                        3,
                        "Bella Ciao",
                        "Włoscy partyzanci",
                        "/img/bella-ciao_cover.png",
                        "/audio/bella-ciao.mp3",
                        "/img/bella-ciao_sheet.jpg",
                        listOf("E", "A", "H", "C", "A", "E", "A", "H", "C", "A", "E", "A", "H"),
                    ),
                    MusicTrack(
                        4,
                        "Lulajże Jezuniu",
                        "Nieznany",
                        "/img/jezuniu_cover.png",
                        "/audio/jezuniu.mp3",
                        "/img/jezuniu_sheet.jpg",
                        listOf("E", "E", "E", "G", "F", "E", "F", "D", "D", "F", "A", "G"),
                    ),
                    MusicTrack(
                        5,
                        "Głowa ramiona kolana pięty",
                        "Magda Bogdanowicz",
                        "/img/glowa-ramiona-kolana-piety_cover.png",
                        "/audio/glowa-ramiona-kolana-piety.mp3",
                        "/img/glowa-ramiona-kolana-piety_sheet.jpg",
                        listOf("G", "G", "C", "H", "A", "G", "G", "G", "E", "G"),
                    ),
                )
            repository.saveAll(musicTracks)
        }
    }
}
