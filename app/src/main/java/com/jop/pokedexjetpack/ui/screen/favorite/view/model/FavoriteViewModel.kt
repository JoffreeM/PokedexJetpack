package com.jop.pokedexjetpack.ui.screen.favorite.view.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jop.pokedexjetpack.repositories.local.LocalPokemonImp
import com.jop.pokedexjetpack.ui.screen.favorite.view.state.FavoriteViewState
import com.jop.pokedexjetpack.ui.screen.home.view.state.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val localPokemonImp: LocalPokemonImp,
    application: Application
): AndroidViewModel(application)  {

    private val _state = MutableStateFlow(FavoriteViewState())
    val state: StateFlow<FavoriteViewState> = _state
    init {
        requestPokemonFavorite()
    }

    private fun updateState(state: FavoriteViewState) {
        _state.value = state
    }
    private fun getStateCurrent(): FavoriteViewState {
        return _state.value
    }

    private fun requestPokemonFavorite() = viewModelScope.launch {
        updateIsLoading(true)
        val state: FavoriteViewState = getStateCurrent()
        val pokemonFavorite = localPokemonImp.getPokemonFavorites()
        updateState(state.copy(pokemonFavoriteList = pokemonFavorite, isLoading = false))
    }

    private fun updateIsLoading(isLoading: Boolean){
        val state: FavoriteViewState = getStateCurrent()
        updateState(state.copy(isLoading = isLoading))
    }
}