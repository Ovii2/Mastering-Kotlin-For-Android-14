package com.example.chapter_seven.navigation

sealed interface ContentType {
    data object List : ContentType
    data object ListAndDetail: ContentType
}