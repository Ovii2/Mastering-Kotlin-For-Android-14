package com.example.chapter_five

import android.app.Application
import com.example.chapter_five.di.appModules
import org.koin.core.context.startKoin

class ChapterFiveApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin() {
            modules(appModules)
        }
    }
}