package com.app.laserharp.external.storage

import com.app.laserharp.domain.MusicTrack
import com.app.laserharp.external.storage.MusicTrackEntity.Companion.TABLE_NAME
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = TABLE_NAME)
data class MusicTrackEntity(
    @Id val id: Int? = null,
    val title: String,
    val artist: String,
    val imageUrl: String,
    val audioUrl: String,
    val sheetMusicUrl: String,
    val notes: String,
) {
    constructor() : this(null, "", "", "", "", "", "")

    fun toDomain(): MusicTrack {
        return MusicTrack(
            id = this.id ?: error("ID cannot be null"),
            title = this.title,
            artist = this.artist,
            imageUrl = this.imageUrl,
            audioUrl = this.audioUrl,
            sheetMusicUrl = this.sheetMusicUrl,
            notes = this.notes.split("-"),
        )
    }

    companion object {
        const val TABLE_NAME = "music_track"

        fun from(musicTrack: MusicTrack): MusicTrackEntity {
            return MusicTrackEntity(
                id = musicTrack.id,
                title = musicTrack.title,
                artist = musicTrack.artist,
                imageUrl = musicTrack.imageUrl,
                audioUrl = musicTrack.audioUrl,
                sheetMusicUrl = musicTrack.sheetMusicUrl,
                notes = musicTrack.notes.joinToString("-"),
            )
        }
    }
}
