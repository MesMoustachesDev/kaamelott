package com.useradgents.data.network

import com.useradgents.domain.model.Burger
import io.reactivex.Single
import retrofit2.http.GET

interface BurgerAPIService {

    @GET("catalog")
    fun getBurgers(): Single<List<Burger>>
}