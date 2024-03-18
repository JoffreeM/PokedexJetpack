package com.jop.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Pokemon")
data class PokemonEntity(
    @PrimaryKey
    val idPokemon: Int,
    val name: String,
    val imageUrl: String,
    val isFavorite: Boolean
)
