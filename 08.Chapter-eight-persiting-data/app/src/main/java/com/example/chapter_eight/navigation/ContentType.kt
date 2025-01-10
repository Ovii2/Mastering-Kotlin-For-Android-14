package com.example.chapter_eight.navigation

sealed interface ContentType {
    data object List : ContentType
    data object ListAndDetail: ContentType
}