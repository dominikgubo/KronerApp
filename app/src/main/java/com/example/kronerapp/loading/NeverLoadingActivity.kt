package com.example.kronerapp.loading

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.kronerapp.classes.MusicMethod
import com.example.kronerapp.classes.SoundMethod
import com.example.kronerapp.databinding.ActivityNeverLoadingBinding
import com.example.kronerapp.neverHaveIEverActivity

class NeverLoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNeverLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNeverLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val checkboxStudentValue = intent.getStringExtra("StudentCheckbox")
        val checkboxDriverValue = intent.getStringExtra("DriverCheckbox")
        val checkboxAdultValue = intent.getStringExtra("AdultCheckbox")

        MusicMethod.player.pause()

        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@NeverLoadingActivity, neverHaveIEverActivity::class.java)
            if(checkboxStudentValue=="true"){
                intent.putExtra("StudentCheckbox","true")
            }
            if(checkboxDriverValue=="true"){
                intent.putExtra("DriverCheckbox","true")
            }
            if(checkboxAdultValue=="true"){
                intent.putExtra("AdultCheckbox","true")
            }
            //intent.putExtra("Pause",length)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)
    }
}