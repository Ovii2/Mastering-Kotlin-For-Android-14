package com.example.chapter_eleven

import com.example.chapter_eleven.data.model.Cat
import com.example.chapter_eleven.data.repository.PetsRepository
import com.example.chapter_eleven.viewModel.PetsViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class PetsViewModelTest {
    private val petsRepository = mockk<PetsRepository>(relaxed = true)
    private lateinit var petsViewModel: PetsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        petsViewModel = PetsViewModel(petsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetPets() = runTest {
        val cats = listOf(
            Cat(
                id = "1",
                tags = listOf("cute", "fluffy"),
                isFavorite = false,
                mimetype = "Test",
                size = 1
            )
        )
        // Given
        coEvery { petsRepository.getPets() } returns flowOf(cats)
        // When
        petsViewModel.getPets()
        coVerify { petsRepository.getPets() }
        // Then
        val uiState = petsViewModel.petsUIState.value
        assertEquals(cats, uiState.pets)
    }
}