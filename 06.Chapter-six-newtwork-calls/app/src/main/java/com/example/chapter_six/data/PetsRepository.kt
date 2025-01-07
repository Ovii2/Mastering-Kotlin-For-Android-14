package com.example.chapter_six.data

interface PetsRepository {

    suspend fun getPets(): NetworkResult<List<Cat>>
}