package com.example.simplerick.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.simplerick.MyApp
import com.example.simplerick.room_database.FavoriteCharacterViewModel
import java.lang.reflect.Modifier
import androidx.lifecycle.LiveData
import com.example.simplerick.room_database.FavoriteCharacter
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun FavoritesScreen(favoriteCharacter: FavoriteCharacter,viewModel: FavoriteCharacterViewModel = hiltViewModel()) {


    val favorites by viewModel.favorites.collectAsStateWithLifecycle()


    LazyColumn {
        items(favorites) { favoriteCharacter ->
            Row {
                Text(text = favoriteCharacter.name)
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    viewModel.removeFavorite(favoriteCharacter)
                }) {
                    Text("Remove")
                }
            }
        }
    }
}
