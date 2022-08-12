package com.example.kronerapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.kronerapp.databinding.FragmentPlayersBinding
import android.util.Log

class PlayersFragment : Fragment() {
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)


        var playerOneName = binding.playerOne as EditText
        val playerTwoName = binding.playerTwo as EditText

        binding.startGameButton.setOnClickListener{
            if(playerOneName.text.length<=0 || playerTwoName.text.length<=0){
                Toast.makeText(activity, "Popunite imena igrača!", Toast.LENGTH_SHORT).show()
            }
            else{
            Toast.makeText(activity, "${playerOneName.text} vs. ${playerTwoName.text} ", Toast.LENGTH_SHORT).show()
            val intent = Intent (getActivity(), GameActivity::class.java)
            getActivity()?.startActivity(intent)
            }

        }

        return binding.root
    }
}