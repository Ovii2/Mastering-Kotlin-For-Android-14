package com.example.chapter_five.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chapter_five.viewModel.PetsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetsList(modifier: Modifier) {
    val petsViewModel: PetsViewModel = koinViewModel()
    LazyColumn(modifier = modifier) {
        items(petsViewModel.getPets()) { pet ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Name: ${pet.name}")
                Text("Species: ${pet.species}")
            }

        }
    }
}


