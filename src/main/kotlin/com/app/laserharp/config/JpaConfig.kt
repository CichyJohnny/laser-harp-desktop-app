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
                        "Fur Elise",
                        "Beethoven",
                        "/img/fur_elise_cover.jpg",
                        "/audio/fur_elise.ogg",
                        "/img/fur_elise_sheet.png",
                        listOf(
                            "E5",
                            "D#5",
                            "E5",
                            "D#5",
                            "E5",
                            "B4",
                            "-D5",
                            "-C5",
                            "-A4",
                            "-",
                            "C4",
                            "E4",
                            "A4",
                            "B4-",
                            "E4",
                            "G#4",
                            "B4",
                            "C5-",
                            "E4",
                            "E5",
                            "D#5",
                        ),
                    ),
                    MusicTrack(
                        2,
                        "Midnight Rain",
                        "Lo-Fi Beats",
                        "https://picsum.photos/id/11/100/100",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3",
                        "https://placehold.co/600x200/EEE/31343C?text=Sheet+Music+for+Midnight+Rain",
                        emptyList(),
                    ),
                    MusicTrack(
                        3,
                        "Solar Flare",
                        "Rock Anthem",
                        "https://picsum.photos/id/12/100/100",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3",
                        "https://placehold.co/600x200/EEE/31343C?text=Sheet+Music+for+Neon+Lights",
                        emptyList(),
                    ),
                    MusicTrack(
                        4,
                        "Deep Ocean",
                        "Ambient",
                        "https://picsum.photos/id/13/100/100",
                        "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3",
                        "https://placehold.co/600x200/EEE/31343C?text=Sheet+Music+for+Neon+Lights",
                        emptyList(),
                    ),
                    MusicTrack(
                        5,
                        "Stop Music",
                        "System",
                        "https://picsum.photos/id/20/100/100",
                        "",
                        "https://placehold.co/600x200/EEE/31343C?text=Sheet+Music+for+Neon+Lights",
                        emptyList(),
                    ),
                )
            repository.saveAll(musicTracks)
        }
    }
}
