package com.example.chapter_eleven.data.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.chapter_eleven.data.db.converters.PetsTypeConverters
import com.example.chapter_eleven.data.db.dao.CatDAO
import com.example.chapter_eleven.data.db.entities.CatEntity

@Database(
    entities = [CatEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ]
)

@TypeConverters(PetsTypeConverters::class)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDAO
}