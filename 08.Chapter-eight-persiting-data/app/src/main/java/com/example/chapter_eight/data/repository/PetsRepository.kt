package com.example.chapter_eight.data.repository

import com.example.chapter_eight.data.model.Cat
import kotlinx.coroutines.flow.Flow

interface PetsRepository {

    suspend fun getPets(): Flow<List<Cat>>
    suspend fun fetchRemotePets()
}