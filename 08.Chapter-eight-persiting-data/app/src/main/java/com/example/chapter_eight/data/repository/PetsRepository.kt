package com.example.chapter_eight.data.repository

import com.example.chapter_eight.data.model.Cat
import com.example.chapter_eight.data.network.NetworkResult

interface PetsRepository {

    suspend fun getPets(): NetworkResult<List<Cat>>
}