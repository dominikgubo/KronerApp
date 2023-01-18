package com.qw.kronerapp.loading

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.qw.kronerapp.MainActivity
import com.qw.kronerapp.classes.Music
import com.qw.kronerapp.classes.MusicMethod
import com.qw.kronerapp.databinding.ActivityLoadingBinding


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