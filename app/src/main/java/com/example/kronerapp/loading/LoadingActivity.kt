package com.example.kronerapp.loading

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.kronerapp.MainActivity
import com.example.kronerapp.classes.Music
import com.example.kronerapp.classes.MusicMethod
import com.example.kronerapp.classes.SoundMethod
import com.example.kronerapp.databinding.ActivityLoadingBinding


class LoadingActivity : AppCompatActivity() {
    var songMediaPlayer: MediaPlayer? = null
    private lateinit var binding: ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var SongList = Music()
        MusicMethod.SoundPlayer(this,SongList.getRandomSong())
        YoYo.with(Techniques.FadeIn).duration(2500).repeat(0).playOn(binding.logo)
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@LoadingActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out)
            finish()
        }, 2100)
    }
}