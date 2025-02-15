package com.example.chapter_seven.views.screens

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
import com.example.chapter_seven.data.model.Cat
import com.example.chapter_seven.navigation.ContentType
import com.example.chapter_seven.viewModel.PetsViewModel
import com.example.chapter_seven.views.PetListAndDetails
import com.example.chapter_seven.views.PetsList
import com.example.chapter_seven.views.state.PetsUIState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetsScreen(onPetClicked: (Cat) -> Unit, contentType: ContentType) {
    val petsViewModel: PetsViewModel = koinViewModel()
    val petsUIState by petsViewModel.petsUIState.collectAsStateWithLifecycle()

    PetsScreenContent(modifier = Modifier.fillMaxSize(), onPetClicked = onPetClicked, contentType = contentType, petsUIState = petsUIState)
}

@Composable
fun PetsScreenContent(modifier: Modifier, onPetClicked: (Cat) -> Unit, contentType: ContentType, petsUIState: PetsUIState) {
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
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                PetListAndDetails(
                    pets = petsUIState.pets
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