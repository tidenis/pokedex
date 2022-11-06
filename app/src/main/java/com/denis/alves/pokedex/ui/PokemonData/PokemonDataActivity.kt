package com.denis.alves.pokedex.ui.PokemonData

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.denis.alves.pokedex.R
import com.denis.alves.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.pokemon_data.*

class PokemonDataActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pokemon_data)

        viewModel = ViewModelProvider(this).get(PokemonDataViewModel::class.java)

        initUI()
    }

    private fun initUI() {
        val id = intent.extras?.get("id") as Int

        viewModel.getPokemonInfo(id)

        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            setPokemonBasicData(pokemon)

            setPokemonType(pokemon)

            setPokemonStats(pokemon)
        })
    }

    private fun setPokemonBasicData(pokemon: Pokemon) {
        Glide.with(this).load(pokemon.sprites.other.official.front_default).into(ivPokemonImage)
        tvPokemonName.text = pokemon.name.capitalize()
        tvPokemonNumber.text = "Nº ${pokemon.number}"
    }

    private fun setPokemonStats(pokemon: Pokemon) {
        tvValuePokemonHpStat.text = pokemon.stats[0].base_stat.toString()
        progressBarHp.setProgress(pokemon.stats[0].base_stat)
        progressBarHp.progressTintList = ColorStateList.valueOf(
            when{
                pokemon.stats[0].base_stat in 0..59 -> Color.parseColor("#FF7F0F")
                pokemon.stats[0].base_stat in 60..99 -> Color.parseColor("#FFDD57")
                pokemon.stats[0].base_stat in 100..119 -> Color.parseColor("#A0E515")
                pokemon.stats[0].base_stat in 120..149 -> Color.parseColor("#23CD5E")
                pokemon.stats[0].base_stat > 149 -> Color.parseColor("#00C2b8")
                else -> Color.parseColor("#00FFFFFF")
            })

        tvValuePokemonAttackStat.text = pokemon.stats[1].base_stat.toString()
        progressBarAttack.setProgress(pokemon.stats[1].base_stat)
        progressBarAttack.progressTintList = ColorStateList.valueOf(
            when{
                pokemon.stats[1].base_stat in 0..59 -> Color.parseColor("#FF7F0F")
                pokemon.stats[1].base_stat in 60..99 -> Color.parseColor("#FFDD57")
                pokemon.stats[1].base_stat in 100..119 -> Color.parseColor("#A0E515")
                pokemon.stats[1].base_stat in 120..149 -> Color.parseColor("#23CD5E")
                pokemon.stats[1].base_stat > 149 -> Color.parseColor("#00C2b8")
                else -> Color.parseColor("#00FFFFFF")
            })

        tvValuePokemonDefenseStat.text = pokemon.stats[2].base_stat.toString()
        progressBarDefense.setProgress(pokemon.stats[2].base_stat)
        progressBarDefense.progressTintList = ColorStateList.valueOf(
            when{
                pokemon.stats[2].base_stat in 0..59 -> Color.parseColor("#FF7F0F")
                pokemon.stats[2].base_stat in 60..99 -> Color.parseColor("#FFDD57")
                pokemon.stats[2].base_stat in 100..119 -> Color.parseColor("#A0E515")
                pokemon.stats[2].base_stat in 120..149 -> Color.parseColor("#23CD5E")
                pokemon.stats[2].base_stat > 149 -> Color.parseColor("#00C2b8")
                else -> Color.parseColor("#00FFFFFF")
            })

        tvValuePokemonSpAttackStat.text = pokemon.stats[3].base_stat.toString()
        progressBarSpAttack.setProgress(pokemon.stats[3].base_stat)
        progressBarSpAttack.progressTintList = ColorStateList.valueOf(
            when{
                pokemon.stats[3].base_stat in 0..59 -> Color.parseColor("#FF7F0F")
                pokemon.stats[3].base_stat in 60..99 -> Color.parseColor("#FFDD57")
                pokemon.stats[3].base_stat in 100..119 -> Color.parseColor("#A0E515")
                pokemon.stats[3].base_stat in 120..149 -> Color.parseColor("#23CD5E")
                pokemon.stats[3].base_stat > 149 -> Color.parseColor("#00C2b8")
                else -> Color.parseColor("#00FFFFFF")
            })

        tvValuePokemonSpDefenseStat.text = pokemon.stats[4].base_stat.toString()
        progressBarSpDefense.setProgress(pokemon.stats[4].base_stat)
        progressBarSpDefense.progressTintList = ColorStateList.valueOf(
            when{
                pokemon.stats[4].base_stat in 0..59 -> Color.parseColor("#FF7F0F")
                pokemon.stats[4].base_stat in 60..99 -> Color.parseColor("#FFDD57")
                pokemon.stats[4].base_stat in 100..119 -> Color.parseColor("#A0E515")
                pokemon.stats[4].base_stat in 120..149 -> Color.parseColor("#23CD5E")
                pokemon.stats[4].base_stat > 149 -> Color.parseColor("#00C2b8")
                else -> Color.parseColor("#00FFFFFF")
            })

        tvValuePokemonSpeedStat.text = pokemon.stats[5].base_stat.toString()
        progressBarSpeed.setProgress(pokemon.stats[5].base_stat)
        progressBarSpeed.progressTintList = ColorStateList.valueOf(
            when{
                pokemon.stats[5].base_stat in 0..59 -> Color.parseColor("#FF7F0F")
                pokemon.stats[5].base_stat in 60..99 -> Color.parseColor("#FFDD57")
                pokemon.stats[5].base_stat in 100..119 -> Color.parseColor("#A0E515")
                pokemon.stats[5].base_stat in 120..149 -> Color.parseColor("#23CD5E")
                pokemon.stats[5].base_stat > 149 -> Color.parseColor("#00C2b8")
                else -> Color.parseColor("#00FFFFFF")
            })

        var total = 0;
        for (i in 0..5) {
            total = total + pokemon.stats[i].base_stat
        }
        tvValueTotalStats.text = total.toString()
    }

    private fun setPokemonType(pokemon: Pokemon) {
        tvPokemonType1.text = pokemon.types[0].type.name
        when (tvPokemonType1.text) {
            "normal" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeNormal
                )
            )
            "fire" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeFire
                )
            )
            "water" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeWater
                )
            )
            "electric" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeEletric
                )
            )
            "grass" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeGrass
                )
            )
            "ice" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeIce
                )
            )
            "fighting" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeFighting
                )
            )
            "poison" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypePoison
                )
            )
            "ground" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeGround
                )
            )
            "flying" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeflying
                )
            )
            "psychic" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypePsychic
                )
            )
            "bug" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeBug
                )
            )
            "rock" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeRock
                )
            )
            "ghost" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeGhost
                )
            )
            "dragon" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeDragon
                )
            )
            "dark" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeDark
                )
            )
            "steel" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeSteel
                )
            )
            "fairy" -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTypeFairy
                )
            )
            else -> tvPokemonType1.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    R.color.colorTransparent
                )
            )
        }

        if (pokemon.types.count() > 1) {
            tvPokemonType2.text = pokemon.types[1].type.name
            when (tvPokemonType2.text) {
                "normal" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeNormal
                    )
                )
                "fire" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeFire
                    )
                )
                "water" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeWater
                    )
                )
                "electric" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeEletric
                    )
                )
                "grass" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeGrass
                    )
                )
                "ice" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeIce
                    )
                )
                "fighting" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeFighting
                    )
                )
                "poison" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypePoison
                    )
                )
                "ground" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeGround
                    )
                )
                "flying" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeflying
                    )
                )
                "psychic" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypePsychic
                    )
                )
                "bug" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeBug
                    )
                )
                "rock" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeRock
                    )
                )
                "ghost" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeGhost
                    )
                )
                "dragon" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeDragon
                    )
                )
                "dark" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeDark
                    )
                )
                "steel" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeSteel
                    )
                )
                "fairy" -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTypeFairy
                    )
                )
                else -> tvPokemonType2.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.colorTransparent
                    )
                )
            }
        } else {
            tvPokemonType2.visibility = View.GONE
        }
    }
}