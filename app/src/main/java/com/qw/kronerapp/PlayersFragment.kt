package com.qw.kronerapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.qw.kronerapp.databinding.FragmentPlayersBinding
import com.qw.kronerapp.classes.SoundMethod
import com.qw.kronerapp.loading.LoadingAutobusActivity

class PlayersFragment : Fragment() {
    private var _binding: FragmentPlayersBinding? = null
    private val binding get() = _binding!!
    var buttonMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentPlayersBinding.inflate(inflater, container, false)


        var playerOneName = binding.playerOne as EditText
        val playerTwoName = binding.playerTwo as EditText
        val name1 = playerOneName.text
        val name2 = playerTwoName.text



        binding.startGameButton.setOnClickListener{
            if(playerOneName.text.length<=0 || playerTwoName.text.length<=0){
                Toast.makeText(activity, "Popunite imena igrača!", Toast.LENGTH_SHORT).show()
            }
            else{
            Toast.makeText(activity, "${playerOneName.text} vs. ${playerTwoName.text} ", Toast.LENGTH_SHORT).show()
                SoundMethod.SoundPlayerNoLoop(activity,R.raw.menu)
                val intent = Intent (getActivity(), LoadingAutobusActivity::class.java)
                intent.putExtra("playerOneName",name1.toString())
                intent.putExtra("playerTwoName",name2.toString())
                getActivity()?.startActivity(intent)
                activity?.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                activity?.finish()
            }

        }

        return binding.root
    }


}