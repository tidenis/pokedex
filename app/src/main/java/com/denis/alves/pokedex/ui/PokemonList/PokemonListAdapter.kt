package com.denis.alves.pokedex.ui.PokemonList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.denis.alves.pokedex.R
import com.denis.alves.pokedex.model.PokemonResult
import kotlinx.android.synthetic.main.pokemon_data.view.ivPokemonImage
import kotlinx.android.synthetic.main.pokemon_data.view.tvPokemonName
import kotlinx.android.synthetic.main.pokemon_data.view.tvPokemonNumber

class PokemonListAdapter(val pokemonClick : (Int) -> Unit) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>(), Filterable {
    private var pokemonList : MutableList<PokemonResult> = arrayListOf()
    lateinit var pokemonListFilter: MutableList<PokemonResult>

    fun setData(pokemon: MutableList<PokemonResult>){
        this.pokemonList = pokemon
        pokemonListFilter = pokemon
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvPokemonNumber.text = "Nº ${pokemonList[position].number}"
        holder.itemView.tvPokemonName.text = pokemonList[position].name.capitalize()
        Glide.with(holder.itemView.context).load(pokemonList[position].url).into(holder.itemView.ivPokemonImage)
        holder.itemView.setOnClickListener { pokemonClick(pokemonList[position].number) }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

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