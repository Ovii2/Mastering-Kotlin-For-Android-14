package com.example.chapter_nine.navigation

sealed class Screens(val route: String) {
    data object PetsScreen : Screens("pets")
    data object PetDetailScreen : Screens("petDetails")
    data object FavoritePetsScreen : Screens("favoritePets")
}