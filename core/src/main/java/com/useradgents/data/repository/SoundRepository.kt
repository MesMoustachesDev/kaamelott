package com.useradgents.data.repository

import com.useradgents.domain.model.Sound
import io.reactivex.Observable

interface SoundRepository {
    fun getSounds(): Observable<Sound>
    fun getSoundsWithIds(sounds: List<String>): Observable<Sound>
}