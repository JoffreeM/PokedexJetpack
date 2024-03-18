package com.jop.pokedexjetpack.ui.screen.home.view.model

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.jop.pokedexjetpack.repositories.pokemon.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.AndroidViewModel
import com.jop.domain.entities.PokemonEntity
import com.jop.pokedexjetpack.repositories.local.LocalPokemonImp
import com.jop.pokedexjetpack.ui.items.ItemDropdownMenu
import com.jop.pokedexjetpack.ui.screen.home.view.state.HomeViewState
import com.jop.pokedexjetpack.utils.isNetworkAvailable
import com.jop.pokedexjetpack.utils.urlIdPokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val localPokemonImp: LocalPokemonImp,
    application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState> = _state

    init {
        requestDataInit()
    }

    private fun updateState(state: HomeViewState) {
        _state.value = state
    }
    private fun getStateCurrent(): HomeViewState{
        return _state.value
    }

    private fun requestDataInit() = viewModelScope.launch {
        if(isNetworkAvailable()){
            updateIsLoading(true)
            val state: HomeViewState = getStateCurrent()
            if (localPokemonImp.countPokemon() == 0) requestAndInsertPokemonLocal()
            delay(1000)
            val typePokemonList = repository.getTypesPokemon().results.map {
                ItemDropdownMenu(id = urlIdPokemon(it.url).toString(), name = it.name)
            }
            val listPokemon = localPokemonImp.getAllPokemon().sortedBy { it.name }
            updateState(state.copy(pokemonList = listPokemon,typePokemonList = typePokemonList, isLoading = false))
        }
    }

    fun requestPokemonLocale() = viewModelScope.launch {
        updateIsLoading(true)
        val state: HomeViewState = getStateCurrent()
        updateState(state.copy(pokemonList = emptyList()))
        val listPokemon = localPokemonImp.getAllPokemon().sortedBy { it.name }
        updateState(state.copy(pokemonList = listPokemon, typePokemonSelected = ItemDropdownMenu(), valueSearch = "", isLoading = false))
    }

    fun getNamePokemonRealTime(startsWith: String) = viewModelScope.launch {
        val state: HomeViewState = getStateCurrent()
        updateState(state.copy(pokemonList = emptyList()))
        if (startsWith.isEmpty()){
            updateState(state.copy(pokemonList = localPokemonImp.getAllPokemon().sortedBy { it.name }))
        }else{
            updateState(state.copy(pokemonList = localPokemonImp.getNamePokemonRealTime(startsWith)))
        }
    }

    fun updateSearch(value: String){
        val state: HomeViewState = getStateCurrent()
        updateState(state.copy(valueSearch = value))
        getNamePokemonRealTime(value)
    }

    fun updateSelectedType(value: ItemDropdownMenu) = viewModelScope.launch {
        val state: HomeViewState = getStateCurrent()
        updateState(state.copy(pokemonList = emptyList()))
        if (value.id.isEmpty()){
            updateState(state.copy(pokemonList = localPokemonImp.getAllPokemon().sortedBy { it.name },typePokemonSelected = value))
        }else{
            val idsPokemon = repository.getFilterTypesPokemon(value.id.toInt()).pokemon.map { urlIdPokemon(it.pokemon.url) }
            updateState(state.copy(pokemonList = localPokemonImp.getSelectTypePokemon(idsPokemon), typePokemonSelected = value))
        }
    }

    private fun updateIsLoading(isLoading: Boolean){
        val state: HomeViewState = getStateCurrent()
        updateState(state.copy(isLoading = isLoading))
    }

    fun updateFavorite(pokemonId: Int, isFavorite: Boolean) = viewModelScope.launch {
        localPokemonImp.updatePokemonFavorite(pokemonId, isFavorite)
    }

    fun requestDetailsPokemon(idPokemon: Int) = viewModelScope.launch {
        updateIsLoading(true)
        val state: HomeViewState = getStateCurrent()
        if(isNetworkAvailable()){
            val details = repository.getDetailsPokemon(idPokemon)
            updateState(state.copy(dataDetailPokemon = details, isLoading = false))
        }else{
            updateState(state.copy(shoModalPokemonDetails = false, isLoading = false))
        }
    }

    fun updateShowModalDetails(showModal: Boolean){
        val state: HomeViewState = getStateCurrent()
        updateState(state.copy(shoModalPokemonDetails = showModal))
    }

    private fun requestAndInsertPokemonLocal() = viewModelScope.launch  {
        val listPokemon = repository.getPokemonGeneralList()
        localPokemonImp.insertPokemon(
            pokemon = listPokemon.map {
                PokemonEntity(
                    idPokemon = it.idPokemon,
                    name = it.name,
                    imageUrl = it.urlImage,
                    isFavorite = false
                )
            }
        )
    }

}