package com.example.chapter_eight.navigation

sealed interface NavigationType {
    data object BottomNavigation : NavigationType
    data object NavigationDrawer : NavigationType
    data object NavigationRail : NavigationType
}