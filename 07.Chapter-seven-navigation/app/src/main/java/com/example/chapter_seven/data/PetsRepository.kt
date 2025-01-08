package com.example.chapter_seven.data

interface PetsRepository {

    suspend fun getPets(): NetworkResult<List<Cat>>
}