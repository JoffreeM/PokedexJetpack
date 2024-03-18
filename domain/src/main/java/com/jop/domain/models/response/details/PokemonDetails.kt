package com.jop.domain.models.response.details

data class PokemonDetails(
    val name: String,
    val imageUrl: String,
    val type: String,
    val hp: Int,
    val speed: Int,
    val attack: Int,
    val defense: Int,
    val specialAttack: Int,
    val specialDefense: Int,
    val height: Int,
    val weight: Int
)
