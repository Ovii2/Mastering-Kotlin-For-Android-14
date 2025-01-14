package com.example.chapter_ten.navigation

sealed interface ContentType {
    data object List : ContentType
    data object ListAndDetail: ContentType
}