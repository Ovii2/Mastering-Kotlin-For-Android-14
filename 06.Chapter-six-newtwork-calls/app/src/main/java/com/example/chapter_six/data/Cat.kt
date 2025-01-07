package com.example.chapter_six.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cat(
//    @SerialName("createdAt")
//    val createdAt: String,
//    @SerialName("_id")
//    val id: String,
//    @SerialName("owner")
//    val owner: String,
//    @SerialName("tags")
//    val tags: List<String>,
//    @SerialName("updateAt")
//    val updatedAt: String


    @SerialName("_id")
    val id: String,
    @SerialName("mimetype")
    val mimetype: String,
    @SerialName("size")
    val size: Int,
    @SerialName("tags")
    val tags: List<String>,

    )

