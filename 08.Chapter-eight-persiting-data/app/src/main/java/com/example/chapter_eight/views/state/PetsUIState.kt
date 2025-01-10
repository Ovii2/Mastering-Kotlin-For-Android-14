package com.example.chapter_eight.views.state

import com.example.chapter_eight.data.model.Cat

data class PetsUIState(
    val isLoading: Boolean = false,
    val pets: List<Cat> = emptyList(),
    val error: String? = null
)