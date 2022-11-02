package com.denis.alves.pokedex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    @Expose @SerializedName("count") val count: Int,
    @Expose @SerializedName("next") val next: String,
    @Expose @SerializedName("previous") val previous: String,
    @Expose @SerializedName("results") var results: ArrayList<PokemonResult>
)

data class PokemonResult (
    @Expose @SerializedName("id") var number: Int,
    @Expose @SerializedName("name") var name: String,
    @Expose @SerializedName("url") var url: String
)