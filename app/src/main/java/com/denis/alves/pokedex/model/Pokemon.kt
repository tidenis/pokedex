package com.denis.alves.pokedex.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @Expose @SerializedName("id") val number: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("sprites") val sprites: Sprites,
    @Expose @SerializedName("types") val types: List<Types>,
    @Expose @SerializedName("stats") val stats: List<Stats>
)

data class Sprites(
    @Expose @SerializedName("other") val other: Other
)

data class Other(
    @Expose @SerializedName("official-artwork") val official: OfficialArtwork
)

data class OfficialArtwork(
    @Expose @SerializedName("front_default") val front_default: String?
)

data class Types(
    @Expose @SerializedName("type") val type: Type
)

data class Type(
    @Expose @SerializedName("name") val name: String?
)

data class Stats(
    @Expose @SerializedName("base_stat") val base_stat: Int,
    @Expose @SerializedName("stat") val stat: Stat
)

data class Stat(
    @Expose @SerializedName("name") val name: String?
)