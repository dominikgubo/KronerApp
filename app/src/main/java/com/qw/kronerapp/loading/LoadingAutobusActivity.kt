package com.qw.kronerapp.loading

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.qw.kronerapp.GameActivity
import com.qw.kronerapp.classes.MusicMethod
import com.qw.kronerapp.databinding.ActivityLoadingAutobusBinding

class LoadingAutobusActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoadingAutobusBinding
    var songMediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingAutobusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val playerOneName = intent.getStringExtra("playerOneName")
        val playerTwoName = intent.getStringExtra("playerTwoName")
        MusicMethod.player.pause()

        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@LoadingAutobusActivity, GameActivity::class.java)
            intent.putExtra("playerOneName",playerOneName)
            intent.putExtra("playerTwoName",playerTwoName)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 3000)
    }
}