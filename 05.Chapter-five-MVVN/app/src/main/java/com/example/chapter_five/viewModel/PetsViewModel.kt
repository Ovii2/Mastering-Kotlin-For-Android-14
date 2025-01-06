package com.example.chapter_five.viewModel

import androidx.lifecycle.ViewModel
import com.example.chapter_five.data.PetsRepository

class PetsViewModel(private val petsRepository: PetsRepository): ViewModel() {

    fun getPets() = petsRepository.getPets()

}