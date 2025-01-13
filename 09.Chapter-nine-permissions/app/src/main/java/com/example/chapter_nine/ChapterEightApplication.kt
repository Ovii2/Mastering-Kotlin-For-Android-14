package com.example.chapter_nine

import android.app.Application
import com.example.chapter_nine.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin


class ChapterEightApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(appModules)
            workManagerFactory()
        }
    }
}