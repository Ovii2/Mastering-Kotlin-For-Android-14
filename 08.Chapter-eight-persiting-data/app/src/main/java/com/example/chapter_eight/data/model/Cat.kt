package com.example.chapter_eight.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat(

    @SerialName("_id")
    val id: String,
    @SerialName("mimetype")
    val mimetype: String,
    @SerialName("size")
    val size: Int,
    @SerialName("tags")
    val tags: List<String>,
)