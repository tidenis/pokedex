package com.denis.alves.pokedex.ui.PokemonList

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denis.alves.pokedex.R
import com.denis.alves.pokedex.model.PokemonResult
import com.denis.alves.pokedex.ui.PokemonData.PokemonDataActivity
import kotlinx.android.synthetic.main.pokemon_list.*

class PokemonListActivity : AppCompatActivity()
{
    val limit = 151
    private lateinit var viewModel : PokemonListViewModel
    private lateinit var searchView: SearchView
    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_list)
        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)
        searchView = findViewById(R.id.svSearchPokemon)
        searchView.clearFocus()

        initUI()
    }

    private fun initUI() {
        iniciateAdapter()

        getPokemon(limit)

        pokemonListObserve()

        checkSearchPokemon()
    }

    private fun iniciateAdapter()
    {
        rvPokemon.layoutManager = LinearLayoutManager(this)

        rvPokemon.adapter = PokemonListAdapter {
            val intent = Intent(this, PokemonDataActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }
    }

    private fun getPokemon(limit: Int)
    {
        viewModel.getPokemonList(limit)
    }

    private fun pokemonListObserve()
    {
        viewModel.pokemonListComplete.observe(this, Observer { list->
            (rvPokemon.adapter as PokemonListAdapter).setData(list as MutableList<PokemonResult>)
        })
    }

    private fun checkSearchPokemon()
    {
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