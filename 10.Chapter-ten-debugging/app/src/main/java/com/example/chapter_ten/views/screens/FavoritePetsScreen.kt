package com.example.chapter_ten.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chapter_ten.data.model.Cat
import com.example.chapter_ten.viewModel.PetsViewModel
import com.example.chapter_ten.views.PetListItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritePetsScreen(
    onPetClicked: (Cat) -> Unit
) {
    val petsViewModel: PetsViewModel = koinViewModel()
    LaunchedEffect(Unit) {
        petsViewModel.getFavoritePets()
    }
    val pets by petsViewModel.favoritePets.collectAsStateWithLifecycle()

    if (pets.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No pets")
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(pets) { pet ->
                PetListItem(
                    cat = pet,
                    onPetClicked = onPetClicked,
                    onFavoriteClicked = {
                        petsViewModel.updatePet(it)
                    }
                )
            }
        }
    }
}