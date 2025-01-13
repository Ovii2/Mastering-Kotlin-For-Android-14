package com.example.chapter_nine.views.screens

import android.Manifest
import android.widget.Toast
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chapter_nine.data.PermissionAction
import com.example.chapter_nine.data.model.Cat
import com.example.chapter_nine.navigation.ContentType
import com.example.chapter_nine.viewModel.PetsViewModel
import com.example.chapter_nine.views.PetListAndDetails
import com.example.chapter_nine.views.PetsList
import com.example.chapter_nine.views.permissions.PermissionDialog
import com.example.chapter_nine.views.state.PetsUIState
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetsScreen(
    onPetClicked: (Cat) -> Unit,
    contentType: ContentType
) {
    val petsViewModel: PetsViewModel = koinViewModel()
    val petsUIState by petsViewModel.petsUIState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var showContent by rememberSaveable { mutableStateOf(false) }

    PermissionDialog(
        context = context,
        permission = Manifest.permission.ACCESS_COARSE_LOCATION
    ) { permissionAction ->
        when (permissionAction) {
            is PermissionAction.PermissionDenied -> {
                showContent = false
            }

            is PermissionAction.PermissionGranted -> {
                showContent = true
                Toast.makeText(context, "Location permission granted!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    if (showContent) {
        PetsScreenContent(
            modifier = Modifier.fillMaxSize(), onPetClicked = onPetClicked, contentType = contentType, petsUIState = petsUIState,
            onFavoriteClicked = {
                petsViewModel.updatePet(it)
            }
        )
    }
}

@Composable
fun PetsScreenContent(
    modifier: Modifier,
    onPetClicked: (Cat) -> Unit,
    contentType: ContentType,
    petsUIState: PetsUIState,
    onFavoriteClicked: (Cat) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = petsUIState.isLoading
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
                Text("Loading...")
            }
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