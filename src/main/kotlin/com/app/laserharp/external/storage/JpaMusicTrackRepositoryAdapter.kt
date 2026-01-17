package com.app.laserharp.external.storage

import com.app.laserharp.domain.MusicTrack
import com.app.laserharp.domain.MusicTrackRepository
import org.springframework.stereotype.Repository

@Repository
class JpaMusicTrackRepositoryAdapter(private val jpa: JpaMusicTrackRepository) : MusicTrackRepository {

    override fun findById(id: Int): MusicTrack =
        jpa.findById(id).orElseThrow { NoSuchElementException("Music track not found for id=$id") }.toDomain()

    override fun findAll(): List<MusicTrack> = jpa.findAll().map { it.toDomain() }

    override fun save(track: MusicTrack): MusicTrack = jpa.save(MusicTrackEntity.from(track)).toDomain()

    override fun saveAll(tracks: List<MusicTrack>): List<MusicTrack> {
        val tracksEntities = tracks.map { MusicTrackEntity.from(it) }
        return jpa.saveAll(tracksEntities).map { it.toDomain() }
    }

    override fun count(): Long = jpa.count()
}
