package com.example.chapter_six.di

import com.example.chapter_six.data.CatsAPI
import com.example.chapter_six.data.PetsRepository
import com.example.chapter_six.data.PetsRepositoryImpl
import com.example.chapter_six.viewModel.PetsViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    single<PetsRepository> { PetsRepositoryImpl() }

    single { PetsViewModel(get()) }

    single {
        Retrofit.Builder().addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
            .baseUrl("https://cataas.com/api/").build()
    }

    single {
        get<Retrofit>().create(CatsAPI::class.java)
    }
}