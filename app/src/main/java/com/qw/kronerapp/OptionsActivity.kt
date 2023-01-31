package com.qw.kronerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.qw.kronerapp.classes.AppLifeCycleHelper
import com.qw.kronerapp.classes.MusicMethod
import com.qw.kronerapp.classes.SoundMethod
import com.qw.kronerapp.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        YoYo.with(Techniques.Shake).duration(2500).repeat(Animation.INFINITE).playOn(binding.optionTitle)
        binding.musicChange.text="Toggle Music"
        binding.musicChange.setOnClickListener {
            MusicMethod.toggleMusic(this)
        }
    }

    override fun onBackPressed() {
        SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }



 override fun onResume() {
     super.onResume()
     if (MusicMethod.player != null) {
         MusicMethod.player.start()
     }
 }
}