package br.com.mcontigo.teste.isearch.presentation.splashscreen

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.mcontigo.teste.isearch.R
import br.com.mcontigo.teste.isearch.presentation.songlist.SongListActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setupHandler()
    }

    private fun setupHandler() {
        val handler = Handler()
        handler.postDelayed({ goToNextScreen() }, 4000)
    }

    private fun goToNextScreen() {
        finish()
        startActivity(Intent(this, SongListActivity::class.java))
    }

}

