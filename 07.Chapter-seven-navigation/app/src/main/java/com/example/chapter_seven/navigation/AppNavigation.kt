package com.example.chapter_seven.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.chapter_seven.views.PetDetailsScreen
import com.example.chapter_seven.views.PetsScreen
import kotlinx.serialization.json.Json


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.PetsScreen.route) {
        composable(Screens.PetsScreen.route) {
            PetsScreen(onPetClicked = { cat ->
                navController.navigate(Screens.PetDetailScreen.route)
            })
        }
        composable("${Screens.PetDetailScreen.route}/{cat}") {
            PetDetailsScreen(
                onBackPress = { navController.popBackStack() },
                cat = Json.decodeFromString(it.arguments?.getString("cat") ?: ""),
            )
        }
    }
}