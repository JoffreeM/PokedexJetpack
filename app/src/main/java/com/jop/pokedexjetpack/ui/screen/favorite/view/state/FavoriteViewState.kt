package com.jop.pokedexjetpack.ui.screen.favorite.view.state

import com.jop.domain.entities.PokemonEntity

data class FavoriteViewState (
    var isLoading: Boolean = false,
    var pokemonFavoriteList: List<PokemonEntity> = emptyList()
)