package com.jop.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jop.domain.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: List<PokemonEntity>)

    @Query("SELECT * FROM Pokemon")
    suspend fun getAllPokemon(): List<PokemonEntity>

    @Query("SELECT COUNT(*) FROM Pokemon")
    suspend fun countPokemon(): Int

    @Query("SELECT * FROM Pokemon WHERE  name LIKE :startsWith || '%' ")
    suspend fun getNamePokemonRealTime(startsWith: String): List<PokemonEntity>

    @Query("SELECT * FROM Pokemon WHERE idPokemon IN (:idsPokemon)")
    suspend fun getSelectTypePokemon(idsPokemon: List<Int>): List<PokemonEntity>

    @Query("UPDATE Pokemon SET isFavorite = :favorite WHERE idPokemon = :pokemonId")
    suspend fun updatePokemonFavorite(pokemonId: Int, favorite: Boolean)

    @Query("SELECT * FROM Pokemon WHERE isFavorite = 1")
    suspend fun getPokemonFavorites(): List<PokemonEntity>
}