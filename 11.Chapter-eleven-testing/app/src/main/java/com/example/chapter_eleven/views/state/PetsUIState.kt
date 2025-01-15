package com.example.chapter_eleven.views.state

import com.example.chapter_eleven.data.model.Cat

data class PetsUIState(
    val isLoading: Boolean = false,
    val pets: List<Cat> = emptyList(),
    val error: String? = null
)