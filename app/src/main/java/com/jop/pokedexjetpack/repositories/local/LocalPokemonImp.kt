package com.jop.pokedexjetpack.repositories.local

import com.jop.database.DBPokemon
import com.jop.domain.entities.PokemonEntity
import javax.inject.Inject

class LocalPokemonImp @Inject constructor(
    private val dbPokemon: DBPokemon
) {
    suspend fun insertPokemon(pokemon: List<PokemonEntity>){
        dbPokemon.PokemonLocale().insertPokemon(pokemon)
    }

    suspend fun countPokemon(): Int{
        return dbPokemon.PokemonLocale().countPokemon()
    }

    suspend fun getAllPokemon(): List<PokemonEntity>{
        return dbPokemon.PokemonLocale().getAllPokemon()
    }

    suspend fun getNamePokemonRealTime(startsWith: String): List<PokemonEntity>{
        return dbPokemon.PokemonLocale().getNamePokemonRealTime(startsWith)
    }

    suspend fun updatePokemonFavorite(pokemonId: Int, favorite: Boolean){
        dbPokemon.PokemonLocale().updatePokemonFavorite(pokemonId, favorite)
    }

    suspend fun getSelectTypePokemon(idsPokemon: List<Int>): List<PokemonEntity>{
        return dbPokemon.PokemonLocale().getSelectTypePokemon(idsPokemon)
    }

    suspend fun getPokemonFavorites(): List<PokemonEntity>{
        return dbPokemon.PokemonLocale().getPokemonFavorites()
    }
}