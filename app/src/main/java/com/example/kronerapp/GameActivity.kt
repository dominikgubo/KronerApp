package com.example.kronerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kronerapp.databinding.ActivityGameBinding
import com.example.kronerapp.databinding.ActivityMainBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    var cards = arrayOf(R.drawable.sas,
    R.drawable.bas,
    R.drawable.zas,
    R.drawable.las,
    R.drawable.skralj,
    R.drawable.lkralj,
    R.drawable.zkralj,
    R.drawable.bkralj,
    R.drawable.sdama,
    R.drawable.bdama,
    R.drawable.ldama,
    R.drawable.zdama,
    R.drawable.sdecko,
    R.drawable.bdecko,
    R.drawable.ldecko,
    R.drawable.zdecko,
    R.drawable.s10,
    R.drawable.b10,
    R.drawable.z10,
    R.drawable.l10,
    R.drawable.s9,
    R.drawable.z9,
    R.drawable.l9,
    R.drawable.b9,
    R.drawable.s8,
    R.drawable.l8,
    R.drawable.b8,
    R.drawable.z8,
    R.drawable.s7,
    R.drawable.b7,
    R.drawable.z7,
    R.drawable.l7)


}