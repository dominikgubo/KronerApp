package com.example.kronerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import com.example.kronerapp.databinding.ActivityLocalStatisticsBinding
import com.example.kronerapp.databinding.ActivityMainBinding

class LocalStatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalStatisticsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}