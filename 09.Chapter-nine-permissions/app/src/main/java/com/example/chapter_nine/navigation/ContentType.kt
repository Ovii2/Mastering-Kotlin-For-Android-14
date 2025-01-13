package com.example.chapter_nine.navigation

sealed interface ContentType {
    data object List : ContentType
    data object ListAndDetail: ContentType
}