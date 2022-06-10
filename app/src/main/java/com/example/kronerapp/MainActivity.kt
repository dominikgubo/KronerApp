package com.example.kronerapp

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kronerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val randomText = arrayOf("Odlična prilika za osramotiti se!","Love and Drinking that's what I'm thinking","Fun and interactive", "kroner kroner kroner", "Korpak vara", "PIPL MAST TRAST US")
        val randIndex = (0..randomText.size).random()
        binding.welcome.text=randomText[randIndex]
        binding.toNewGame.setOnClickListener{
            //perform an operation
            //send_Data is the button's id
            var intent = Intent(this,ModeActivity::class.java)
            startActivity(intent)
        }
    }


}