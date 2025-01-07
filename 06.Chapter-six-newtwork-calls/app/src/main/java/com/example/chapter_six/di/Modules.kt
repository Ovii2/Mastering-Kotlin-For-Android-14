package com.example.chapter_six.di

import com.example.chapter_six.data.CatsAPI
import com.example.chapter_six.data.PetsRepository
import com.example.chapter_six.data.PetsRepositoryImpl
import com.example.chapter_six.viewModel.PetsViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit


val appModules = module {
    val baseUrl = "https://cataas.com/api/"

    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }
    }

//    single<PetsRepository> { PetsRepositoryImpl(get(), get()) }
//    single { PetsViewModel(get()) }

    singleOf(::PetsRepositoryImpl) { bind<PetsRepository>() }
    singleOf(::PetsViewModel)

    single { Dispatchers.IO }


    single {
        Retrofit.Builder().addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
            .baseUrl(baseUrl).build()
    }

    single {
        get<Retrofit>().create(CatsAPI::class.java)
    }
}