package com.useradgents.domain.model

import com.google.gson.annotations.SerializedName

/**
 * /!\ SerializedName is SUPER important for Proguard
 */
data class Burger(
        @SerializedName("ref") var ref: String? = null,
        @SerializedName("thumbnail") var thumbnail: String? = null,
        @SerializedName("price") var price: Float? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("title") var title: String? = null
)
