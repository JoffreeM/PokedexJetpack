package com.jop.pokedexjetpack.ui.screen.home.view.state

import com.jop.domain.entities.PokemonEntity
import com.jop.domain.models.response.details.PokemonDetails
import com.jop.pokedexjetpack.ui.items.ItemDropdownMenu

data class HomeViewState(
    var isLoading: Boolean = false,
    var pokemonList: List<PokemonEntity> = emptyList(),
    var valueSearch: String = "",
    var typePokemonList: List<ItemDropdownMenu> = emptyList(),
    var typePokemonSelected: ItemDropdownMenu = ItemDropdownMenu(),
    var dataDetailPokemon: PokemonDetails? = null,
    var shoModalPokemonDetails: Boolean = false
)
