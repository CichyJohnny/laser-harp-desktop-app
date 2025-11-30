package com.app.laserharp.external.client.model

import com.app.laserharp.domain.MusicTrack

data class MusicTrackRequest(val id: Int, val notes: List<String>) {
    companion object {
        fun from(domain: MusicTrack) = MusicTrackRequest(id = domain.id, notes = domain.notes)
    }
}
