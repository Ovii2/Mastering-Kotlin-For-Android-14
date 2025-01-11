package com.example.chapter_eight.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chapter_eight.data.db.entities.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catEntity: CatEntity)

    @Query("SELECT * FROM Cat")
    fun getCats(): Flow<List<CatEntity>>
}