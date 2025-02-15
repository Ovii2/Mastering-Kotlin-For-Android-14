package com.example.chapter_ten

import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.example.chapter_ten.data.workers.PetsSyncWorkers
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PetSyncWorkerTest {

    @get:Rule
    val koinTestRule = KoinTestRule()

    @Before
    fun setUp() {
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager((ApplicationProvider.getApplicationContext()), config)
    }

    @Test
    fun testPetsSyncWorker() {
        val syncPetsWorkRequest = OneTimeWorkRequestBuilder<PetsSyncWorkers>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .setRequiresBatteryNotLow(true)
                    .build()
            )
            .build()


        // Setting up Test Drivers
        val workManager = WorkManager.getInstance(ApplicationProvider.getApplicationContext())
        val testDriver = WorkManagerTestInitHelper.getTestDriver(ApplicationProvider.getApplicationContext())!!

        // Enqueue and wait for result.
        workManager.enqueueUniqueWork(
            uniqueWorkName = "PetsSyncWorker",
            existingWorkPolicy = ExistingWorkPolicy.KEEP,
            requests = listOf(syncPetsWorkRequest)
        ).result.get()

        // Get WorkInfo and outputData
        val workInfo = workManager.getWorkInfoById(syncPetsWorkRequest.id).get()

        // Assert
        assertEquals(WorkInfo.State.ENQUEUED, workInfo?.state)

        // Tells the testing framework that the constraints have been met
        testDriver.setAllConstraintsMet(syncPetsWorkRequest.id)

        // Get WorkInfo and outputData
        val postRequirementWorkInfo = workManager.getWorkInfoById(syncPetsWorkRequest.id).get()
        assertEquals(WorkInfo.State.RUNNING, postRequirementWorkInfo?.state)

    }
}