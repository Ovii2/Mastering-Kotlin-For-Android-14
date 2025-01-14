package com.example.chapter_ten.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.chapter_ten.data.repository.PetsRepository

class PetsSyncWorkers(
    appContext: Context,
    workerParameters: WorkerParameters,
    private val petsRepository: PetsRepository
) : CoroutineWorker(appContext, workerParameters) {
    override suspend fun doWork(): Result {
        return try {
            petsRepository.fetchRemotePets()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}