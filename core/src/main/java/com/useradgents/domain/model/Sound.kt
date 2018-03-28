package com.useradgents.domain.model

import com.google.gson.annotations.SerializedName

/**
 * /!\ SerializedName is SUPER important for Proguard
 */
data class Sound(
        @SerializedName("character") var character: String? = null,
        @SerializedName("episode") var episode: String? = null,
        @SerializedName("file") var file: String? = null,
        @SerializedName("title") var title: String? = null
)
