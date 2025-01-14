package com.example.chapter_ten

import android.app.Application
import com.example.chapter_ten.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin


class ChapterTenApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(appModules)
            workManagerFactory()
        }
    }
}