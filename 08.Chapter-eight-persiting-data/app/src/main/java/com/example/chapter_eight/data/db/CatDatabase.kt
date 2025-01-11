package com.example.chapter_eight.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chapter_eight.data.db.converters.PetsTypeConverters
import com.example.chapter_eight.data.db.dao.CatDAO
import com.example.chapter_eight.data.db.entities.CatEntity

@Database(
    entities = [CatEntity::class],
    version = 1
)

@TypeConverters(PetsTypeConverters::class)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDAO
}