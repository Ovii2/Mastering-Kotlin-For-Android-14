package com.example.chapter_nine.views.state

import com.example.chapter_nine.data.model.Cat

data class PetsUIState(
    val isLoading: Boolean = false,
    val pets: List<Cat> = emptyList(),
    val error: String? = null
)