package com.example.simplerick.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.network.model.TvCharacter
import com.example.simplerick.viewmodel.TvCharacterViewModel

@Composable
fun CharacterDetail(character: TvCharacter, viewModel: TvCharacterViewModel = hiltViewModel()) {
    //val isFavorite = viewModel.isFavorite(character)

    val favorites by viewModel.favorites.observeAsState(mutableListOf())
    val isFavorite = favorites.contains(character)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = character.name, style = MaterialTheme.typography.h4)
        Text(text = character.status, style = MaterialTheme.typography.body1)
        Text(text = character.species, style = MaterialTheme.typography.body1)
        Text(text = character.gender, style = MaterialTheme.typography.body1)
        Text(text = "Location: ${character.location.name}", style = MaterialTheme.typography.body1)

        Button(onClick = {
            if (isFavorite) {
                viewModel.removeFromFavorites(character)
            } else {
                viewModel.addToFavorites(character)
            }
        }) {
            Text(if (isFavorite) "Remove from Favorites" else "Add to Favorites")
        }
    }
}

