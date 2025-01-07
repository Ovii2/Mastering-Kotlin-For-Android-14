package com.example.chapter_six.data

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatsAPI {

    @GET("cats")
    suspend fun fetchCats(
        @Query("tag") tag: String,
    ): Response<List<Cat>>
}