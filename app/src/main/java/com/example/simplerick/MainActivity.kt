package com.example.simplerick

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplerick.screens.CharacterDetail
import com.example.simplerick.screens.CharacterList
import com.example.simplerick.ui.theme.SimpleRickTheme
import com.example.simplerick.viewmodel.TvCharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp {
                SetupNavGraph()
        }
    }
}

    @Composable
    fun MyApp(content: @Composable () -> Unit) {
        MaterialTheme {
            content()
        }
    }

    @Composable
    fun SetupNavGraph() {
        val navController = rememberNavController()

        NavHost(navController, startDestination = "characterList") {
            composable("characterList") {
                CharacterListScreen(navController)
            }
            composable("characterDetail/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId")?.toInt()
                // Retrieve the character details based on the ID
                characterId?.let { CharacterDetailScreen(characterId) }
            }
        }
    }

    @Composable
    fun CharacterListScreen(navController: NavController, viewModel: TvCharacterViewModel = hiltViewModel()) {
        viewModel.fetchCharacters()
        CharacterList(viewModel) { character ->
            navController.navigate("characterDetail/${character.id}")
        }
    }

    @Composable
    fun CharacterDetailScreen(characterId: Int, viewModel: TvCharacterViewModel = hiltViewModel()) {

        val characters by viewModel.characters.observeAsState(emptyList())
        val character = characters.find { it.id == characterId }
        character?.let { CharacterDetail(it) }
    }
}
