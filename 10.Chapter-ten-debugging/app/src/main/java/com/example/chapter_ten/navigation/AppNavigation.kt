package com.example.chapter_ten.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chapter_ten.data.model.Cat
import com.example.chapter_ten.views.screens.FavoritePetsScreen
import com.example.chapter_ten.views.screens.PetDetailsScreen
import com.example.chapter_ten.views.screens.PetsScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder


//@Composable
//fun AppNavigation() {
//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = Screens.PetsScreen.route) {
//        composable(Screens.PetsScreen.route) {
//            PetsScreen(onPetClicked = { cat ->
//               navController.navigate("${Screens.PetDetailScreen.route}/${Json.encodeToString(cat)}")
//            })
//        }
//        composable(route = "${Screens.PetDetailScreen.route}/{catId}",
//            arguments = listOf(navArgument("catId") {
//                type = NavType.StringType
//            }
//            )) {
//            PetDetailsScreen(
//                onBackPress = { navController.popBackStack() },
//                cat = Json.decodeFromString(it.arguments?.getString("cat") ?: ""),
//            )
//        }
//    }
//}

@Composable
fun AppNavigation(
    contentType: ContentType,
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(navController = navHostController, startDestination = Screens.PetsScreen.route) {
        composable(Screens.PetsScreen.route) {
            PetsScreen(onPetClicked = { cat ->
                val encodedCat = URLEncoder.encode(Json.encodeToString(cat), "UTF-8")
                navHostController.navigate("${Screens.PetDetailScreen.route}/$encodedCat")
            }, contentType = contentType)
        }
        composable(
            route = "${Screens.PetDetailScreen.route}/{catId}",
            arguments = listOf(
                navArgument("catId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val encodedCat = backStackEntry.arguments?.getString("catId") ?: ""
            val decodedCat = URLDecoder.decode(encodedCat, "UTF-8")
            val cat = Json.decodeFromString<Cat>(decodedCat)
            PetDetailsScreen(
                onBackPressed = { navHostController.popBackStack() },
                cat = cat
            )
        }
        composable(Screens.FavoritePetsScreen.route) {
            FavoritePetsScreen(
                onPetClicked = { cat ->
                    navHostController.navigate(
                        "${Screens.PetDetailScreen.route}/${Json.encodeToString(cat)}}"
                    )
                }
            )
        }
    }
}