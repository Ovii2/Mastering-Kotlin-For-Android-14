package com.example.chapter_ten.data.repository

import com.example.chapter_ten.data.model.Cat
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface CatsAPI {
    @GET("cats")
    suspend fun fetchCats(
        @Query("tag") tag: String,
    ): Response<List<Cat>>
}