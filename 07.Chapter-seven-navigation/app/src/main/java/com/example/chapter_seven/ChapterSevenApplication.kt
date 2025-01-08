package com.example.chapter_seven

import android.app.Application
import com.example.chapter_seven.di.appModules
import org.koin.core.context.startKoin


class ChapterSevenApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}