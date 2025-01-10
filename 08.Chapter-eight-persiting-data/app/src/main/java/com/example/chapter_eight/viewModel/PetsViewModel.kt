package com.example.chapter_eight.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chapter_eight.data.network.NetworkResult
import com.example.chapter_eight.data.repository.PetsRepository
import com.example.chapter_eight.views.state.PetsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PetsViewModel(private val petsRepository: PetsRepository) : ViewModel() {

    val petsUIState = MutableStateFlow(PetsUIState())

    init {
        getPets()
    }

    private fun getPets() {
        petsUIState.value = PetsUIState(isLoading = true)
        viewModelScope.launch {
            when (val result = petsRepository.getPets()) {
                is NetworkResult.Success -> {
                    petsUIState.update {
                        it.copy(isLoading = false, pets = result.data)
                    }
                }

                is NetworkResult.Error -> {
                    petsUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }
}