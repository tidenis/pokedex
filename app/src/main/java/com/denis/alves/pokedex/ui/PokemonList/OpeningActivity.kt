package com.denis.alves.pokedex.ui.PokemonList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.denis.alves.pokedex.R

class OpeningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opening_activity)
        startSecondActivity()
    }

    private fun startSecondActivity()
    {
        var handler = Handler()

        handler.postDelayed({
            val intent = Intent(this, PokemonListActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}