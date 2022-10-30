package com.denis.alves.pokedex.ui.PokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denis.alves.pokedex.R
import com.denis.alves.pokedex.databinding.PokemonListBinding
import com.denis.alves.pokedex.model.PokemonResult
import kotlinx.android.synthetic.main.pokemon_data.view.*

class PokemonListAdapter(val pokemonClick : (Int) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>(), Filterable {
    private var pokemonList: MutableList<PokemonResult> = arrayListOf()
    lateinit var pokemonListFilter: MutableList<PokemonResult>

    fun setData(list: MutableList<PokemonResult>) {
        pokemonList = list
        pokemonListFilter = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        pokemonList[position].number = position + 1
        var imagePokemon = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonList[position].number}.png"
        pokemonList[position].url = imagePokemon

        holder.itemView.tvPokemonNumber.text = "Nº ${pokemonList[position].number}"
        holder.itemView.tvPokemonName.text = pokemonList[position].name.capitalize()
        Glide.with(holder.itemView.context).load(imagePokemon).into(holder.itemView.ivPokemonImage)
        holder.itemView.setOnClickListener { pokemonClick(pokemonList[position].number) }
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getFilter(): Filter {
        return object : Filter()
        {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if(query == null || query.length < 0)
                {
                    filterResults.count = pokemonListFilter.size
                    filterResults.values = pokemonListFilter
                }else {

                    val itemPokemonFiltered : MutableList<PokemonResult> = arrayListOf()

                    for (item in pokemonListFilter) {
                        if (item.name.contains(query.toString().toLowerCase())) {
                            item.url = item.url.replace("https://pokeapi.co/api/v2/pokemon/", "")
                            item.url = item.url.replace("/", "")
                            item.number = item.url.toInt()
                            itemPokemonFiltered.add(item)
                        }
                    }

                    filterResults.count = itemPokemonFiltered.size
                    filterResults.values = itemPokemonFiltered
                }
                return filterResults
            }

            override fun publishResults(query: CharSequence?, filterResults: FilterResults?) {
                if (filterResults != null) {
                    pokemonList = filterResults.values as MutableList<PokemonResult>
                    notifyDataSetChanged()
                }
            }
        }
    }
}