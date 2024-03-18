package com.jop.pokedexjetpack.repositories.pokemon

import com.jop.domain.models.response.details.PokemonDetails
import com.jop.domain.models.response.pokemon.Pokemon
import com.jop.domain.models.response.pokemon.PokemonResponse
import com.jop.domain.models.response.type.PokemonTypes
import com.jop.pokedexjetpack.utils.urlIdPokemon
import com.jop.pokedexjetpack.utils.urlImageAltered
import com.jop.web.ApiService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPokemonGeneralList(): List<Pokemon> {
        val pokemon = apiService.getPokemonList().results
        return pokemon.map {
            Pokemon(
                idPokemon = urlIdPokemon(it.url),
                name = it.name,
                url = it.url,
                urlImage = urlImageAltered(it.url)
            )
        }
    }

    suspend fun getDetailsPokemon(idPokemon: Int): PokemonDetails {
        val pokemonDetailsResponse = apiService.getDetailsPokemon(idPokemon)
        return PokemonDetails(
            name = pokemonDetailsResponse.name,
            imageUrl = pokemonDetailsResponse.sprites.front_default,
            type = pokemonDetailsResponse.types.first().type.name,
            hp = pokemonDetailsResponse.stats[0].base_stat,
            speed = pokemonDetailsResponse.stats[5].base_stat,
            attack = pokemonDetailsResponse.stats[4].base_stat,
            defense = pokemonDetailsResponse.stats[3].base_stat,
            specialAttack = pokemonDetailsResponse.stats[2].base_stat,
            specialDefense = pokemonDetailsResponse.stats[1].base_stat,
            height = pokemonDetailsResponse.height,
            weight = pokemonDetailsResponse.weight
        )
    }

    suspend fun getTypesPokemon(): PokemonResponse {
        return apiService.getTypesPokemon()
    }

    suspend fun getFilterTypesPokemon(id: Int): PokemonTypes {
        return apiService.getFilterTypesPokemon(id)
    }
}