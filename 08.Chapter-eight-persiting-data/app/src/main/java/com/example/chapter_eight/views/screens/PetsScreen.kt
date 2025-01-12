package com.example.chapter_eight.views.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chapter_eight.data.model.Cat
import com.example.chapter_eight.navigation.ContentType
import com.example.chapter_eight.viewModel.PetsViewModel
import com.example.chapter_eight.views.PetListAndDetails
import com.example.chapter_eight.views.PetsList
import com.example.chapter_eight.views.state.PetsUIState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetsScreen(onPetClicked: (Cat) -> Unit, contentType: ContentType) {
    val petsViewModel: PetsViewModel = koinViewModel()
    val petsUIState by petsViewModel.petsUIState.collectAsStateWithLifecycle()

    PetsScreenContent(
        modifier = Modifier.fillMaxSize(), onPetClicked = onPetClicked, contentType = contentType, petsUIState = petsUIState,
        onFavoriteClicked = {
            petsViewModel.updatePet(it)
        }
    )
}

@Composable
fun PetsScreenContent(
    modifier: Modifier,
    onPetClicked: (Cat) -> Unit,
    contentType: ContentType,
    petsUIState: PetsUIState,
    onFavoriteClicked: (Cat) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        AnimatedVisibility(
            visible = petsUIState.isLoading
        ) {
            CircularProgressIndicator()
        }
        AnimatedVisibility(
            visible = petsUIState.pets.isNotEmpty()
        ) {
            if (contentType == ContentType.List) {
                PetsList(
                    onPetClicked = onPetClicked,
                    pets = petsUIState.pets,
                    modifier = Modifier.fillMaxWidth(),
                    onFavoriteClicked = onFavoriteClicked
                )
            } else {
                PetListAndDetails(
                    pets = petsUIState.pets,
                    onFavoriteClicked = onFavoriteClicked
                )
            }
        }
        AnimatedVisibility(
            visible = petsUIState.error != null
        ) {
            Text(text = petsUIState.error ?: "")
        }
    }
}