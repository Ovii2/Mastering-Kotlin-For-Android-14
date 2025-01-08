package com.example.chapter_seven.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.chapter_seven.data.Cat
import com.example.chapter_seven.viewModel.PetsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PetsList(
    onPetClicked: (Cat) -> Unit,
    modifier: Modifier
) {
    val petsViewModel: PetsViewModel = koinViewModel()
    val petsUIState by petsViewModel.petsUIState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.padding(top = 16.dp, end = 16.dp, bottom = 50.dp, start = 16.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(100.dp))
        AnimatedVisibility(
            visible = petsUIState.isLoading
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text("Loading...")
            }
        }
        AnimatedVisibility(visible = petsUIState.pets.isNotEmpty()) {
            LazyColumn {
                items(petsUIState.pets) { pet ->
                    PetListItem(
                        cat = pet,
                        onPetClicked = onPetClicked
                    )
                }
            }
        }
        AnimatedVisibility(visible = petsUIState.error != null) {
            Text(text = petsUIState.error ?: "")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PetListItem(cat: Cat, onPetClicked: (Cat) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .clickable {
                    onPetClicked(cat)
                }
        ) {
            AsyncImage(
                model = "https://cataas.com/cat/${cat.id}", contentDescription = "Cute cat", modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )
            FlowRow(modifier = Modifier.padding(start = 6.dp, end = 6.dp)) {
                repeat(cat.tags.size) {
                    SuggestionChip(
                        modifier = Modifier.padding(start = 3.dp, end = 3.dp), onClick = {}, label = {
                            Text(text = cat.tags[it])
                        }
                    )
                }
            }
        }
    }
}
