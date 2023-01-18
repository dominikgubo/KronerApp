package com.qw.kronerapp

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import androidx.core.view.isVisible
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.qw.kronerapp.classes.MusicMethod
import com.qw.kronerapp.classes.PokerCards
import com.qw.kronerapp.classes.SoundMethod
import com.qw.kronerapp.databinding.ActivityKingsCupBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.random.Random

class KingsCupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKingsCupBinding
    var cardMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKingsCupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.clickForNext.isVisible = false
        MusicMethod.player.start()
        var playingDeck = PokerCards()
        var random = Random.nextInt(playingDeck.cardId.size-1)
        var counter = 1
        var kingCounter = 0
        binding.currentCardImage.setImageResource(playingDeck.cardId[random])
        when (playingDeck.cardValue[random]) {
            2 -> {
                binding.currentCardMeaning.text = "2 - Odaberi nekog da popije gutljaj cuge \uD83E\uDD1D"
            }
            3 -> {
                binding.currentCardMeaning.text = "3 - Popij gutljaj cuge \uD83C\uDF79"
            }
            4 -> {
                binding.currentCardMeaning.text = "4 - Tko zadnji dotakne nos pije \uD83D\uDC43"
            }
            5 -> {
                binding.currentCardMeaning.text = "5 - Dečki piju \uD83D\uDC66"
            }
            6 -> {
                binding.currentCardMeaning.text = "6 - Cure piju \uD83D\uDC67"
            }
            7 -> {
                binding.currentCardMeaning.text = "7 - Tko zadnji podigne ruke pije \uD83D\uDE4C"
            }
            8 -> {
                binding.currentCardMeaning.text = "8 - Odaberi prijatelja za cuganje, kad god ti piješ pije i on \uD83D\uDC65"
            }
            9 -> {
                binding.currentCardMeaning.text = "9 - Odaberi kategoriju, u krug nabrajajte stvari iz kategorije. Prvi tko ne zna pije \uD83D\uDCCA"
            }
            10 -> {
                binding.currentCardMeaning.text = "10 - Svi piju, živili \uD83C\uDF7B"
            }
            11 -> {
                binding.currentCardMeaning.text = "DEČKO - Postavi pravilo koje traje do kraja igre, tko prekrši pravilo pije \uD83D\uDC48"
            }
            12 -> {
                binding.currentCardMeaning.text = "DAMA - Postavljaš 'Never have I ever' upit, piju svi koji su navedenu stvar napravili \uD83D\uDE2F"
            }
            13 -> {
                kingCounter++
                if(kingCounter!=4){
                    binding.currentCardMeaning.text = "KRALJ - Sipaš piće u čašu. Tko izvuče zadnjeg kralja pije iz čaše. BROJAČ: "+kingCounter.toString()+"/4 \uD83E\uDD64"
                } else{
                    binding.currentCardMeaning.text = "KRALJ - Ups na tebi je red, pij "+kingCounter.toString()+"/4 \uD83D\uDC40"
                }
            }
            14 -> {
                binding.currentCardMeaning.text = "AS - Kreneš piti i svi piju sve dok ti ne završiš \uD83D\uDC4D"
            }
        }
        playingDeck.cardId.removeAt(random)
        playingDeck.cardValue.removeAt(random)


        binding.nextButton.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.cardflip)
           // var randomOnClick=0
            //if(playingDeck.cardId.size!=1){
                var randomOnClick = Random.nextInt(playingDeck.cardId.size)
            //}
            binding.currentCardImage.setImageResource(playingDeck.cardId[randomOnClick])
            when (playingDeck.cardValue[randomOnClick]) {
                2 -> {
                    binding.currentCardMeaning.text = "2 - Odaberi nekog da popije gutljaj cuge \uD83E\uDD1D"
                }
                3 -> {
                    binding.currentCardMeaning.text = "3 - Popij gutljaj cuge \uD83C\uDF79"
                }
                4 -> {
                    binding.currentCardMeaning.text = "4 - Tko zadnji dotakne nos pije \uD83D\uDC43"
                }
                5 -> {
                    binding.currentCardMeaning.text = "5 - Dečki piju \uD83D\uDC66"
                }
                6 -> {
                    binding.currentCardMeaning.text = "6 - Cure piju \uD83D\uDC67"
                }
                7 -> {
                    binding.currentCardMeaning.text = "7 - Tko zadnji podigne ruke pije \uD83D\uDE4C"
                }
                8 -> {
                    binding.currentCardMeaning.text = "8 - Odaberi prijatelja za cuganje, kad god ti piješ pije i on \uD83D\uDC65"
                }
                9 -> {
                    binding.currentCardMeaning.text = "9 - Odaberi kategoriju, u krug nabrajajte stvari iz kategorije. Prvi tko ne zna pije \uD83D\uDCCA"
                }
                10 -> {
                    binding.currentCardMeaning.text = "10 - Svi piju, živili \uD83C\uDF7B"
                }
                11 -> {
                    binding.currentCardMeaning.text = "DEČKO - Postavi pravilo koje traje do kraja igre, tko prekrši pravilo pije \uD83D\uDC48"
                }
                12 -> {
                    binding.currentCardMeaning.text = "DAMA - Postavljaš 'Never have I ever' upit, piju svi koji su navedenu stvar napravili \uD83D\uDE2F"
                }
                13 -> {
                    kingCounter++
                    if(kingCounter!=4){
                        binding.currentCardMeaning.text = "KRALJ - Sipaš piće u čašu. Tko izvuče zadnjeg kralja pije iz čaše. BROJAČ: "+kingCounter.toString()+"/4 \uD83E\uDD64"
                    } else{
                        binding.currentCardMeaning.text = "KRALJ - Ups na tebi je red, pij "+kingCounter.toString()+"/4 \uD83D\uDC40"
                    }
                }
                14 -> {
                    binding.currentCardMeaning.text = "AS - Kreneš piti i svi piju sve dok ti ne završiš \uD83D\uDC4D"
                }
            }
            playingDeck.cardId.removeAt(randomOnClick)
            playingDeck.cardValue.removeAt(randomOnClick)
            counter++
            binding.currentCardNumber.text = counter.toString()+"/52"
            if(counter==52){
                binding.clickForNext.isVisible = true
                binding.nextButton.text="Nova igra"
                YoYo.with(Techniques.Shake).duration(2500).repeat(Animation.INFINITE).playOn(binding.clickForNext)
                binding.currentCardImage.setOnClickListener{
                    recreate()
                }
                binding.nextButton.setOnClickListener{
                    recreate()
                }
            }
        }

        binding.currentCardImage.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.cardflip)
            //var randomOnClick=0
            //if(playingDeck.cardId.size!=1){
                var randomOnClick = Random.nextInt(playingDeck.cardId.size)
            //} else {
                //randomOnClick
            //}
            binding.currentCardImage.setImageResource(playingDeck.cardId[randomOnClick])
            when (playingDeck.cardValue[randomOnClick]) {
                2 -> {
                    binding.currentCardMeaning.text = "2 - Odaberi nekog da popije gutljaj cuge \uD83E\uDD1D"
                }
                3 -> {
                    binding.currentCardMeaning.text = "3 - Popij gutljaj cuge \uD83C\uDF79"
                }
                4 -> {
                    binding.currentCardMeaning.text = "4 - Tko zadnji dotakne nos pije \uD83D\uDC43"
                }
                5 -> {
                    binding.currentCardMeaning.text = "5 - Dečki piju \uD83D\uDC66"
                }
                6 -> {
                    binding.currentCardMeaning.text = "6 - Cure piju \uD83D\uDC67"
                }
                7 -> {
                    binding.currentCardMeaning.text = "7 - Tko zadnji podigne ruke pije \uD83D\uDE4C"
                }
                8 -> {
                    binding.currentCardMeaning.text = "8 - Odaberi prijatelja za cuganje, kad god ti piješ pije i on \uD83D\uDC65"
                }
                9 -> {
                    binding.currentCardMeaning.text = "9 - Odaberi kategoriju, u krug nabrajajte stvari iz kategorije. Prvi tko ne zna pije \uD83D\uDCCA"
                }
                10 -> {
                    binding.currentCardMeaning.text = "10 - Svi piju, živili \uD83C\uDF7B"
                }
                11 -> {
                    binding.currentCardMeaning.text = "DEČKO - Postavi pravilo koje traje do kraja igre, tko prekrši pravilo pije \uD83D\uDC48"
                }
                12 -> {
                    binding.currentCardMeaning.text = "DAMA - Postavljaš 'Never have I ever' upit, piju svi koji su navedenu stvar napravili \uD83D\uDE2F"
                }
                13 -> {
                    kingCounter++
                    if(kingCounter!=4){
                        binding.currentCardMeaning.text = "KRALJ - Sipaš piće u čašu. Tko izvuče zadnjeg kralja pije iz čaše. BROJAČ: "+kingCounter.toString()+"/4 \uD83E\uDD64"
                    } else{
                        binding.currentCardMeaning.text = "KRALJ - Ups na tebi je red, pij "+kingCounter.toString()+"/4 \uD83D\uDC40"
                    }
                }
                14 -> {
                    binding.currentCardMeaning.text = "AS - Kreneš piti i svi piju sve dok ti ne završiš \uD83D\uDC4D"
                }
            }
            playingDeck.cardId.removeAt(randomOnClick)
            playingDeck.cardValue.removeAt(randomOnClick)
            counter++
            binding.currentCardNumber.text = counter.toString()+"/52"
            if(counter==52){
                binding.clickForNext.isVisible = true
                binding.nextButton.text="Nova igra"
                YoYo.with(Techniques.Shake).duration(2500).repeat(Animation.INFINITE).playOn(binding.clickForNext)
                binding.currentCardImage.setOnClickListener{
                    recreate()
                }
                binding.nextButton.setOnClickListener{
                    recreate()
                }
            }
        }
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this, R.style.DialogTheme).setTitle("Upozorenje")
                .setMessage("Jel ste sigurni da želite izaći iz igre? ").setNegativeButton("Nastavi", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        //    Log.d("Continue", "Game continues.")
                    }
                }).setPositiveButton("Izađi", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        SoundMethod.SoundPlayerNoLoop(this@KingsCupActivity,R.raw.menu)
                        var intent = Intent(this@KingsCupActivity , MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out)
                        finish()
                    }
                }).show()
    }

    override fun onPause() {
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
    }
}