package com.example.kronerapp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.kronerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val randomText = arrayOf("After u patici","Love and Drinking that's what I'm thinking","Fun and interactive", "kroner kroner kroner", "Korpak vara", "Idemo na jedno pivo", "Burek u El Panu", "Dupli pelin, pepeljara i šifra od wifija", "Daenarys Targaryen Zmajka", "www.dominikgubo.com")
        val randIndex = (0..randomText.size-1).random()
        binding.welcome.text=randomText[randIndex]
        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.welcome)
        binding.toNewGame.setOnClickListener{
            //perform an operation
            //send_Data is the button's id
            var intent = Intent(this,ModeActivity::class.java)
            startActivity(intent)
        }
         binding.toRules.setOnClickListener{
            var intent = Intent(this,ModeActivity::class.java)
            startActivity(intent)
        }
        binding.toLocalStatistics.setOnClickListener{
            var intent = Intent(this,ModeActivity::class.java)
            startActivity(intent)
        }
        binding.toGlobalStatistics.setOnClickListener{
            var intent = Intent(this,ModeActivity::class.java)
            startActivity(intent)
        }
    }


}