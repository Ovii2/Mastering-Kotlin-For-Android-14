package com.example.chapter_eleven.data.repository

import com.example.chapter_eleven.data.model.Cat
import kotlinx.coroutines.flow.Flow

interface PetsRepository {

    suspend fun getPets(): Flow<List<Cat>>
    suspend fun fetchRemotePets()
    suspend fun updatePets(cat: Cat)
    suspend fun getFavoritePets(): Flow<List<Cat>>
}