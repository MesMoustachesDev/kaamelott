package com.useradgents.data.network

import com.useradgents.domain.model.Sound
import io.reactivex.Single
import retrofit2.http.GET

interface SoundAPIService {

    @GET("sounds/sounds.a7b9de88.json")
    fun getSounds(): Single<List<Sound>>
}