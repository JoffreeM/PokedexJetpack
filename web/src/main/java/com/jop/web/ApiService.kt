package com.jop.web

import com.jop.domain.models.response.details.PokemonDetailsResponse
import com.jop.domain.models.response.pokemon.PokemonResponse
import com.jop.domain.models.response.type.PokemonTypes
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET("pokemon?limit=100000")
    suspend fun getPokemonList(): PokemonResponse

    @GET("type")
    suspend fun getTypesPokemon(): PokemonResponse

    @GET("type/{id}")
    suspend fun getFilterTypesPokemon(
        @Path("id") idType: Int
    ): PokemonTypes

    @GET("pokemon/{id}")
    suspend fun getDetailsPokemon(
        @Path("id") id: Int
    ): PokemonDetailsResponse
}