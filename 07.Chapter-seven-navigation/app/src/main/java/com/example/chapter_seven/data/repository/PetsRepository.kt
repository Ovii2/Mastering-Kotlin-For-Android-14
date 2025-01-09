package com.example.chapter_seven.data.repository

import com.example.chapter_seven.data.model.Cat
import com.example.chapter_seven.data.network.NetworkResult

interface PetsRepository {

    suspend fun getPets(): NetworkResult<List<Cat>>
}