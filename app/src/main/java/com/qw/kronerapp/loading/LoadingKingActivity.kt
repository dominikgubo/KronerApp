package com.qw.kronerapp.loading

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.qw.kronerapp.KingsCupActivity
import com.qw.kronerapp.classes.MusicMethod
import com.qw.kronerapp.databinding.ActivityLoadingKingBinding


class LoadingKingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingKingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingKingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MusicMethod.player.pause()
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@LoadingKingActivity, KingsCupActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 1100)
    }
}