package com.example.chapter_six

import android.app.Application
import com.example.chapter_six.di.appModules
import org.koin.core.context.startKoin

class ChapterSixApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModules)
        }
    }
}