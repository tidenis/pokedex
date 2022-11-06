package com.denis.alves.pokedex.ui.PokemonList

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.denis.alves.pokedex.R
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class OpeningActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCenter.start(
            application, "e1ea4645-4755-45f5-8b18-59ed940665e7",
            Analytics::class.java, Crashes::class.java
        )
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