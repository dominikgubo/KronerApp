package com.example.kronerapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kronerapp.classes.MusicMethod
import com.example.kronerapp.classes.SoundMethod
import com.example.kronerapp.databinding.ActivityNeverHaveIEverSelectBinding
import com.example.kronerapp.loading.NeverLoadingActivity

class NeverHaveIEverSelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNeverHaveIEverSelectBinding
    private var flagStudent = false
    private var flagDriver = false
    private var flagAdult = false
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_never_have_i_ever_select)


        binding = ActivityNeverHaveIEverSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.adultCheckbox.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this,R.raw.tick)
        }
        binding.studentCheckbox.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this,R.raw.tick)
        }
        binding.driverCheckbox.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this,R.raw.tick)
        }


        binding.neverHaveIEverStart.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.menu)
            var intent = Intent(this, NeverLoadingActivity::class.java)
            if(binding.studentCheckbox.isChecked){
                intent.putExtra("StudentCheckbox","true")
            }
            if(binding.driverCheckbox.isChecked){
                intent.putExtra("DriverCheckbox","true")
            }
            if(binding.adultCheckbox.isChecked){
                intent.putExtra("AdultCheckbox","true")
            }
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out)
            finish()
        }

        binding.studentModeContainer.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this,R.raw.tick)
            if(flagStudent==false || binding.studentCheckbox.isChecked==false){
                binding.studentCheckbox.isChecked = true
            } else if (binding.studentCheckbox.isChecked==true){
                binding.studentCheckbox.isChecked = false
            }else {
                binding.studentCheckbox.isChecked = false
            }
            flagStudent = !flagStudent
        }
        
        binding.driverModeContainer.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this,R.raw.tick)
            if(flagDriver==false || binding.driverCheckbox.isChecked==false){
                binding.driverCheckbox.isChecked = true
            } else if (binding.driverCheckbox.isChecked==true){
                binding.driverCheckbox.isChecked = false
            }else {
                binding.driverCheckbox.isChecked = false
            }
            flagDriver = !flagDriver
        }

        binding.adultModeContainer.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this,R.raw.tick)
            if(flagAdult==false || binding.adultCheckbox.isChecked==false){
                binding.adultCheckbox.isChecked = true
            } else if (binding.adultCheckbox.isChecked==true){
                binding.adultCheckbox.isChecked = false
            }else {
                binding.adultCheckbox.isChecked = false
            }
            flagAdult = !flagAdult
        }
    }

    override fun onBackPressed() {
        SoundMethod.SoundPlayerNoLoop(this,R.raw.menu)
        var intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out)
        finish()
    }

    /*override fun onPause() {
        super.onPause()
        if (MusicMethod.player != null) {
            MusicMethod.player.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (MusicMethod.player != null) {
            MusicMethod.player.start()
        }
    }*/
}