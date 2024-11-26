package com.example.simplerick.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.network.model.TvCharacter
import com.example.simplerick.viewmodel.TvCharacterViewModel



@Composable
fun CharacterList(viewModel: TvCharacterViewModel = hiltViewModel(), onCharacterClick: (TvCharacter) -> Unit){
    val characters by viewModel.characters.observeAsState(emptyList())
    LazyColumn {
        items(characters) { character ->
            CharacterItem(character, onCharacterClick)
        }
    }
}


@Composable
fun CharacterItem(character: TvCharacter, onCharacterClick: (TvCharacter) -> Unit) {
    Row(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .clickable { onCharacterClick(character) }
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.image),
            contentDescription = null,
            modifier = androidx.compose.ui.Modifier.size(64.dp)
        )
        Spacer(modifier = androidx.compose.ui.Modifier.width(16.dp))
        Column {
            Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = character.status, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.width(8.dp))


    }
}

