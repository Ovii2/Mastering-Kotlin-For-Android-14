package com.example.chapter_seven.data.repository

import com.example.chapter_seven.data.model.Cat
import com.example.chapter_seven.data.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PetsRepositoryImpl(
    private val catsAPI: CatsAPI,
    private val dispatcher: CoroutineDispatcher
) : PetsRepository {
    override suspend fun getPets(): NetworkResult<List<Cat>> {
        return withContext(dispatcher) {
            try {
                val response = catsAPI.fetchCats("cute")
                if (response.isSuccessful) {
                    NetworkResult.Success(response.body()!!)
                } else {
                    NetworkResult.Error(response.errorBody().toString())
                }
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}