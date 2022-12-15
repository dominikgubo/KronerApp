package com.example.kronerapp

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.kronerapp.classes.AppLifeCycleHelper
import com.example.kronerapp.classes.MusicMethod
import com.example.kronerapp.classes.SoundMethod
import com.example.kronerapp.databinding.ActivityModeBinding
import com.example.kronerapp.databinding.ActivityRulesBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class RulesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRulesBinding

    var flag = false
    var toggleNever = false
    var toggleAutobus = false
    var toggleKing = false
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRulesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerActivityLifecycleCallbacks(AppLifeCycleHelper())
        binding.autobusRulesButton.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.menu)
            toggleAutobus = !toggleAutobus
            setFragment(AutobusRulesFragment())
            if(toggleAutobus==true){
                binding.neverRulesButton.visibility = View.GONE
                binding.kingRulesButton.visibility = View.GONE
            } else{
                binding.neverRulesButton.visibility = View.VISIBLE
                binding.kingRulesButton.visibility = View.VISIBLE
            }
        }

        binding.neverRulesButton.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.menu)
            toggleNever = !toggleNever
            setFragment(NeverRulesFragment())
            if(toggleNever==true){
                binding.autobusRulesButton.visibility = View.GONE
                binding.kingRulesButton.visibility = View.GONE
            } else{
                binding.autobusRulesButton.visibility = View.VISIBLE
                binding.kingRulesButton.visibility = View.VISIBLE
            }

        }

        binding.kingRulesButton.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.menu)
            toggleKing = !toggleKing
            setFragment(KingRulesFragment())
            if(toggleKing==true){
                binding.autobusRulesButton.visibility = View.GONE
                binding.neverRulesButton.visibility = View.GONE
            } else{
                binding.autobusRulesButton.visibility = View.VISIBLE
                binding.neverRulesButton.visibility = View.VISIBLE
            }

        }


        YoYo.with(Techniques.Shake).duration(2500).repeat(Animation.INFINITE).playOn(binding.rulesTitle)

    }

    private fun setFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerRules, fragment)
        if(flag==false){
            fragmentTransaction.commit()
        } else {
            fragmentTransaction.remove(fragment)
            fragmentTransaction.commit()
        }
        flag = !flag
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