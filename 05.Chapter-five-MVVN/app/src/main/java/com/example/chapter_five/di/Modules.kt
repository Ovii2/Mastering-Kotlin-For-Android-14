package com.example.chapter_five.di

import com.example.chapter_five.data.PetsRepository
import com.example.chapter_five.data.PetsRepositoryImpl
import com.example.chapter_five.viewModel.PetsViewModel
import org.koin.dsl.module

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl() }

    single { PetsViewModel(get()) }
}