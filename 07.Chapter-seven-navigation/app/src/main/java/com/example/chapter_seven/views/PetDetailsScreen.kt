package com.example.chapter_seven.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.chapter_seven.data.Cat


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetDetailsScreen(onBackPress: () -> Unit, cat: Cat) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text("Pet Details")
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary), navigationIcon = {
                IconButton(
                    onClick = onBackPress,
                    content = {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                )
            }
        )
    }, content = { paddingValues ->
        PetDetailsScreenContent(modifier = Modifier.padding(paddingValues), cat = cat)
    })

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PetDetailsScreenContent(modifier: Modifier, cat: Cat) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "https://cataas.com/cat/${cat.id}",
            contentDescription = "Cute cat",
            modifier = modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.FillWidth
        )
        FlowRow(modifier = modifier.padding(start = 6.dp, end = 6.dp)) {
            repeat(2) {
                SuggestionChip(
                    modifier = modifier.padding(start = 3.dp, end = 3.dp),
                    onClick = {},
                    label = {
                        Text("Tag $it")
                    }
                )
            }
        }
    }
}