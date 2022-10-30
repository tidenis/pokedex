package com.denis.alves.pokedex.ui.PokemonData

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denis.alves.pokedex.model.Pokemon
import com.denis.alves.pokedex.service.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDataViewModel() : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonApi = retrofit.create(PokemonApi::class.java)

    val pokemonInfo = MutableLiveData<Pokemon>()

    fun getPokemonInfo(id: Int) {
        val call = service.getPokemonInfo(id)

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    pokemonInfo.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }
}