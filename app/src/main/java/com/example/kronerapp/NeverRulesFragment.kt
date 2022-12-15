package com.example.kronerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kronerapp.databinding.ActivityGameBinding.inflate
import com.example.kronerapp.databinding.ActivityMainBinding.inflate
import com.example.kronerapp.databinding.FragmentAutobusRulesBinding
import com.example.kronerapp.databinding.FragmentNeverRulesBinding
import com.example.kronerapp.databinding.FragmentPlayersBinding


class NeverRulesFragment : Fragment() {


    private var _binding: FragmentNeverRulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentNeverRulesBinding.inflate(inflater, container, false)
        return binding.root
    }


}