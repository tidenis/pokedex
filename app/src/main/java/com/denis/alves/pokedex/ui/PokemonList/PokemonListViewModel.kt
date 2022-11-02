package com.denis.alves.pokedex.ui.PokemonList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.denis.alves.pokedex.model.PokemonResponse
import com.denis.alves.pokedex.model.PokemonResult
import com.denis.alves.pokedex.service.PokemonApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonListViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: PokemonApi = retrofit.create(PokemonApi::class.java)

    var pokemonListComplete = MutableLiveData<List<PokemonResult>>()
    var pokemonList : MutableList<PokemonResult> = arrayListOf()

    fun getPokemonList(limit: Int){
        val call = service.getPokemonList(limit, 0)

        call.enqueue(object : Callback<PokemonResponse> {
            override fun onResponse(
                call: Call<PokemonResponse>,
                response: Response<PokemonResponse>
            ) {
                pokemonList = response.body()!!.results

                var pokemonId = 0

                while(pokemonId < limit)
                {
                    pokemonList
                    pokemonList[pokemonId].number = pokemonId + 1

                    pokemonList[pokemonId].url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    pokemonList[pokemonId].url.replace("/", "")
                    pokemonList[pokemonId].url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonId+1}.png"
                    pokemonId++
                }
                pokemonListComplete.postValue(pokemonList)
            }

            override fun onFailure(call: Call<PokemonResponse>, t: Throwable) {
                call.cancel()
            }
        })
    }
}