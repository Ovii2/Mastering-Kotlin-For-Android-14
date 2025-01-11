package com.example.chapter_eight.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chapter_eight.data.model.Cat
import com.example.chapter_eight.data.network.NetworkResult
import com.example.chapter_eight.data.network.asResult
import com.example.chapter_eight.data.repository.PetsRepository
import com.example.chapter_eight.views.state.PetsUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PetsViewModel(private val petsRepository: PetsRepository) : ViewModel() {

    val petsUIState = MutableStateFlow(PetsUIState())
    private val _favoritePets = MutableStateFlow<List<Cat>>(emptyList())
    val favoritePets: StateFlow<List<Cat>> get() = _favoritePets

    init {
        getPets()
    }

    private fun getPets() {
        petsUIState.value = PetsUIState(isLoading = true)
        viewModelScope.launch {
            petsRepository.getPets().asResult().collect { result ->
                when (result) {
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
}