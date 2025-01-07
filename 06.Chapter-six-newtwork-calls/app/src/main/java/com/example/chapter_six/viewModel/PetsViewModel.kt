package com.example.chapter_six.viewModel

import androidx.lifecycle.ViewModel
import com.example.chapter_six.data.PetsRepository

class PetsViewModel(private val petsRepository: PetsRepository): ViewModel() {

    fun getPets() = petsRepository.getPets()

}