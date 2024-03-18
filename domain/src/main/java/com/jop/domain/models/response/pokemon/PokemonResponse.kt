package com.jop.domain.models.response.pokemon

data class PokemonResponse(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Pokemon> = emptyList()
)
