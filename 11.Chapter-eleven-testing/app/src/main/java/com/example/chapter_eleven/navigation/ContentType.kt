package com.example.chapter_eleven.navigation

sealed interface ContentType {
    data object List : ContentType
    data object ListAndDetail: ContentType
}