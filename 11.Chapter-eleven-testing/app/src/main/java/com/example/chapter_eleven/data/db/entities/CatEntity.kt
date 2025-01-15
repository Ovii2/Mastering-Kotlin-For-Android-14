package com.example.chapter_eleven.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cat")
data class CatEntity(
    @PrimaryKey
    val id: String,
    val mimetype: String,
    val size: Int,
    val tags: List<String>,
    @ColumnInfo(defaultValue = "0")
    val isFavorite: Boolean = false
)
