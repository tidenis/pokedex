package com.denis.alves.pokedex.service

import com.denis.alves.pokedex.model.Pokemon
import com.denis.alves.pokedex.model.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokemonResponse>
}