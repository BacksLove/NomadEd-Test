package com.example.nomadedtest.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Events (
        @SerializedName("title") val title: String,
        @SerializedName("content") val content: String? = "",
        @SerializedName("dateStart") val dateStart: Date,
        @SerializedName("dateEnd") val dateEnd: Date,
        @SerializedName("excerpt") val excerpt: String? = "",
        @SerializedName("medias") val medias: List<EventImage>? = null,
        @SerializedName("address") val address: EventAddress? = null
) : Serializable

data class EventImage (
        @SerializedName("id") val id: String,
        @SerializedName("url") val url: String,
        @SerializedName("type") val type: String
) : Serializable

data class EventAddress (
        @SerializedName("streetAddress") val streetAddress: String,
        @SerializedName("zipCode") val zipCode: String,
        @SerializedName("cityName") val cityName: String,
        @SerializedName("countryName") val countryName: String,
        @SerializedName("countryCode") val countryCode: String
) : Serializable

