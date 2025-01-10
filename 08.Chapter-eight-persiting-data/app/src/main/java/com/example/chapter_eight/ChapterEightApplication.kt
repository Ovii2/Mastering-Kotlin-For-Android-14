package com.example.chapter_eight

import android.app.Application
import com.example.chapter_eight.di.appModules
import org.koin.core.context.startKoin


class ChapterEightApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}