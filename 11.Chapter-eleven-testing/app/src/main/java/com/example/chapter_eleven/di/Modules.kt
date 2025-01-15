package com.example.chapter_eleven.di

import androidx.room.Room.databaseBuilder
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.example.chapter_eleven.data.db.CatDatabase
import com.example.chapter_eleven.data.repository.CatsAPI
import com.example.chapter_eleven.data.repository.PetsRepository
import com.example.chapter_eleven.data.repository.PetsRepositoryImpl
import com.example.chapter_eleven.data.workers.PetsSyncWorkers
import com.example.chapter_eleven.viewModel.PetsViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
    val baseUrl = "https://cataas.com/api/"

    //    Json config
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = true
        }
    }

    //    Repository binding
    singleOf(::PetsRepositoryImpl) { bind<PetsRepository>() }

    //    ViewModel binding
    singleOf(::PetsViewModel)

    // Dispatcher
    single { Dispatchers.IO }

    // Retrofit Setup
//    single {
//        Retrofit.Builder().addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
//            .baseUrl(baseUrl).build()
//
//    }
    // Retrofit Updated Setup
    single {
        Retrofit.Builder().addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
            .client(get())
            .baseUrl(baseUrl).build()
    }

    // CatsAPI
    single {
        get<Retrofit>().create(CatsAPI::class.java)
    }

    // Database and DAO
    single {
        databaseBuilder(
            androidContext(),
            CatDatabase::class.java,
            "cat-database"
        ).build()
    }
    single { get<CatDatabase>().catDao() }

    // Work Manager
    worker { PetsSyncWorkers(get(), get(), get()) }

    // Chucker debug
    single {
        val chuckerCollector = ChuckerCollector(
            context = androidContext(),
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        val chuckerInterceptor = ChuckerInterceptor.Builder(androidContext())
            .collector(chuckerCollector)
            .maxContentLength(250_000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
        OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .build()
    }

}