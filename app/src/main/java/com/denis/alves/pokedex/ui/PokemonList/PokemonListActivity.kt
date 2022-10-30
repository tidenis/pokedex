package com.denis.alves.pokedex.ui.PokemonList

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denis.alves.pokedex.R
import com.denis.alves.pokedex.model.PokemonResponse
import com.denis.alves.pokedex.model.PokemonResult
import com.denis.alves.pokedex.ui.PokemonData.PokemonDataActivity
import kotlinx.android.synthetic.main.pokemon_list.*
import kotlinx.android.synthetic.main.pokemon_list.view.*

class PokemonListActivity : AppCompatActivity()
{
    val limit = 7
    private lateinit var viewModel : PokemonListViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_list)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        searchView = findViewById(R.id.svSearchPokemon)

        initUI()
    }

    private fun initUI() {
        rvPokemon.layoutManager = LinearLayoutManager(this)

        rvPokemon.adapter = PokemonListAdapter {
            val intent = Intent(this, PokemonDataActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        viewModel.getPokemonList(limit)

        searchView.clearFocus()

        viewModel.pokemonList.observe(this, Observer { list ->
            (rvPokemon.adapter as PokemonListAdapter).setData(list as MutableList<PokemonResult>)
        })

        if (searchView != null) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener
            {
                override fun onQueryTextSubmit(query: String?): Boolean
                {
                    (rvPokemon.adapter as PokemonListAdapter).filter.filter(query)
                    return true
                }

                override fun onQueryTextChange(query: String?): Boolean
                {
                    (rvPokemon.adapter as PokemonListAdapter).filter.filter(query)
                    return true
                }
            })
        }
    }
}