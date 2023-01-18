package com.qw.kronerapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qw.kronerapp.databinding.ActivityGameBinding.inflate
import com.qw.kronerapp.databinding.ActivityMainBinding.inflate
import com.qw.kronerapp.databinding.FragmentAutobusRulesBinding


class AutobusRulesFragment : Fragment() {


    private var _binding: FragmentAutobusRulesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAutobusRulesBinding.inflate(inflater, container, false)
        return binding.root
    }


}