package com.jop.pokedexjetpack.ui.screen.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jop.pokedexjetpack.R
import com.jop.pokedexjetpack.ui.composables.CustomDropdownMenu
import com.jop.pokedexjetpack.ui.composables.CustomInput
import com.jop.pokedexjetpack.ui.composables.CustomLoading
import com.jop.pokedexjetpack.ui.composables.CustomModalDetailsPokemon
import com.jop.pokedexjetpack.ui.composables.CustomSpace
import com.jop.pokedexjetpack.ui.composables.CustomToolBar
import com.jop.pokedexjetpack.ui.composables.PokemonListItem
import com.jop.pokedexjetpack.ui.screen.home.view.model.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    onIsDark: (Boolean) -> Unit
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewModel.requestPokemonLocale()
    }
    CustomModalDetailsPokemon(
        showModal = state.shoModalPokemonDetails,
        pokemonDetails = state.dataDetailPokemon,
        onClose = { viewModel.updateShowModalDetails(false) }
    )
    CustomLoading(isLoading = state.isLoading)
    CustomToolBar(
        navController = navController,
        title = R.string.home_title,
        showIconFavorite = true,
        onIsDark = onIsDark
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomInput(
                modifier = Modifier
                    .weight(1f),
                label = R.string.input_placeholder_search,
                value = state.valueSearch,
                onValueChange = {
                    viewModel.updateSearch(it)
                }
            )
            CustomSpace(width = 5)
            CustomDropdownMenu(
                modifier = Modifier
                    .height(55.dp)
                    .weight(.7f),
                optionAllItem = true,
                dataItems = state.typePokemonList,
                value = state.typePokemonSelected.name,
                onValueData = { viewModel.updateSelectedType(it) }
            )
        }
        LazyColumn {
            items(state.pokemonList) { pokemon ->
                PokemonListItem(
                    pokemon = pokemon,
                    viewModel = viewModel,
                    onClickIdPokemon = {
                        viewModel.updateShowModalDetails(true)
                        viewModel.requestDetailsPokemon(pokemon.idPokemon)
                    }
                )
            }
        }
    }
}