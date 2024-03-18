package com.jop.pokedexjetpack.ui.screen.favorite

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jop.pokedexjetpack.R
import com.jop.pokedexjetpack.ui.composables.CustomLoading
import com.jop.pokedexjetpack.ui.composables.CustomToolBar
import com.jop.pokedexjetpack.ui.screen.favorite.view.model.FavoriteViewModel
import com.jop.pokedexjetpack.ui.composables.PokemonListItem

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navController: NavController
){
    val state by viewModel.state.collectAsState()
    CustomLoading(isLoading = state.isLoading)
    CustomToolBar(
        navController = navController,
        showReturnBack = true,
        title = R.string.favorite_title
    ) {
        LazyColumn {
            items(state.pokemonFavoriteList) { pokemon ->
                PokemonListItem(
                    pokemon = pokemon,
                    isActiveFavorite = false,
                    onClickIdPokemon = {}
                )
            }
        }
    }
}