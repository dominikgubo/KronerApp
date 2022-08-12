package com.example.kronerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import androidx.fragment.app.Fragment
import com.example.kronerapp.databinding.ActivityModeBinding
import androidx.fragment.app.FragmentActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo


class ModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.autobusStart.setOnClickListener {
            setFragment(PlayersFragment())
        }

        YoYo.with(Techniques.Shake).duration(2500).repeat(Animation.INFINITE).playOn(binding.gameTitle)
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}