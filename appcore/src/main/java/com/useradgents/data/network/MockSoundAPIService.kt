package com.useradgents.data.network

import com.useradgents.domain.model.Sound
import io.reactivex.Single
import javax.inject.Inject

class MockSoundAPIService @Inject constructor() : SoundAPIService {
    override fun getSounds(): Single<List<Sound>> = Single.just(listOf(Sound(title = "test title")))
}