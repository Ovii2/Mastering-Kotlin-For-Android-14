package com.example.chapter_eleven.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.chapter_eleven.data.db.entities.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(catEntity: CatEntity)

    @Query("SELECT * FROM Cat")
    fun getCats(): Flow<List<CatEntity>>

    @Update
    suspend fun update(catEntity: CatEntity)

    @Query("SELECT * FROM Cat WHERE isFavorite = 1")
    fun getFavoriteCats(): Flow<List<CatEntity>>
}