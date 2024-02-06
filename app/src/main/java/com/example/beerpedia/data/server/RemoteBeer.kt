package com.example.beerpedia.data.server

import com.google.gson.annotations.SerializedName

data class RemoteBeer (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("abv") val abv: Float
)
