package com.app.laserharp.external.storage

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository interface JpaMusicTrackRepository : CrudRepository<MusicTrackEntity, Int>
