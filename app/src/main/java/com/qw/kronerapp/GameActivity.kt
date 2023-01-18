package com.qw.kronerapp

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.qw.kronerapp.databinding.ActivityGameBinding
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.qw.kronerapp.classes.Card
import com.qw.kronerapp.classes.GameStats
import com.qw.kronerapp.classes.MusicMethod
import com.qw.kronerapp.classes.SoundMethod
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.random.Random


class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    var cardMediaPlayer: MediaPlayer? = null
    var beepMediaPlayer: MediaPlayer? = null
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var kronerDbRef = FirebaseDatabase.getInstance().getReference().child("Kroner")

        val playerOneName = intent.getStringExtra("playerOneName")
        val playerTwoName = intent.getStringExtra("playerTwoName")
        var pOneSips = 0
        var pTwoSips = 0
        var round = 1
        var cardsDeck = 28
        var currentPlayer = arrayOf(playerOneName, playerTwoName)
        var currentPlayerIndex = 0
        MusicMethod.player.start()
        binding.playerTag.text = currentPlayer[currentPlayerIndex]
        binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
        binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
        binding.roundNumber.text = "Runda broj: ${round.toString()}"
        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"

        var UsingDeck = Card()
        var random1 = Random.nextInt(UsingDeck.cardId.size-1)
        var random1Value = UsingDeck.cardValue[random1]
        binding.firstCard.setImageResource(UsingDeck.cardId[random1])
       // Log.d("UsingDeck test", UsingDeck.cardValue[random1].toString())
        UsingDeck.cardId.removeAt(random1)
        UsingDeck.cardValue.removeAt(random1)

        var random2 = Random.nextInt(UsingDeck.cardId.size)
        var random2Value = UsingDeck.cardValue[random2]
        binding.secondCard.setImageResource(UsingDeck.cardId[random2])
        UsingDeck.cardId.removeAt(random2)
        UsingDeck.cardValue.removeAt(random2)

        var random3 = Random.nextInt(UsingDeck.cardId.size)
        var random3Value = UsingDeck.cardValue[random3]
        binding.thirdCard.setImageResource(UsingDeck.cardId[random3])
        UsingDeck.cardId.removeAt(random3)
        UsingDeck.cardValue.removeAt(random3)

        var random4 = Random.nextInt(UsingDeck.cardId.size)
        var random4Value = UsingDeck.cardValue[random4]
        binding.fourthCard.setImageResource(UsingDeck.cardId[random4])
        UsingDeck.cardId.removeAt(random4)
        UsingDeck.cardValue.removeAt(random4)

        var positionCounter = 0

        if (positionCounter == 0) {
            binding.firstSelector.isVisible = true
            binding.secondSelector.isVisible = false
            binding.thirdSelector.isVisible = false
            binding.fourthSelector.isVisible = false
            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)
        }


        fun openWarningDialog(){
            if(binding.playerTag.text==playerOneName){
                MaterialAlertDialogBuilder(this, R.style.DialogTheme).setTitle("Upozorenje")
                        .setMessage("Jel ste sigurni da želite predati igru? Ako predate [[ ${playerTwoName.toString()} ]] će pobijediti.").setNegativeButton("Nastavi", object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                            //    Log.d("Continue", "Game continues.")
                            }
                        }).setPositiveButton("Predaj", object : DialogInterface.OnClickListener{
                            @RequiresApi(Build.VERSION_CODES.O)
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                val current = LocalDateTime.now()
                                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                                val formatted = current.format(formatter)
                                val fbStats = GameStats("${playerOneName} - broj gutljaja: ${pOneSips}", "${playerTwoName} - broj gutljaja: ${pTwoSips}", "Pobjednik: ${playerTwoName}", formatted.toString(), "Broj odigranih rundi: ${round}")
                                kronerDbRef.push().setValue(fbStats)
                                var intent = Intent(this@GameActivity , MainActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                                finish()
                            }
                        }).show()
            }
            else{
                MaterialAlertDialogBuilder(this, R.style.DialogTheme).setTitle("Upozorenje")
                        .setMessage("Jel ste sigurni da želite predati igru? Ako predate [[ ${playerOneName.toString()} ]] će pobijediti.").setNegativeButton("Nastavi", object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                              //  Log.d("Continue", "Game continues.")
                            }
                        }).setPositiveButton("Predaj", object : DialogInterface.OnClickListener{
                            @RequiresApi(Build.VERSION_CODES.O)
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                val current = LocalDateTime.now()
                                val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                                val formatted = current.format(formatter)
                                val fbStats = GameStats("${playerOneName} - broj gutljaja: ${pOneSips}", "${playerTwoName} - broj gutljaja: ${pTwoSips}", "Pobjednik: ${playerOneName}", formatted.toString(), "Broj odigranih rundi: ${round}")
                                kronerDbRef.push().setValue(fbStats)
                                var intent = Intent(this@GameActivity , MainActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                                finish()
                            }
                        }).show()
            }
        }


        binding.surrenderButton.setOnClickListener {
            openWarningDialog()
        }


        binding.higherButton.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.cardflip)
            when (positionCounter) {
                0 -> {
                    if(UsingDeck.cardId.size==0){
                        UsingDeck.refillCards()
                        Log.d("Us d", UsingDeck.cardValue.toString())
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }
                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
//                    Log.d("TEST1PRVA", UsingDeck.cardId.size.toString())
//                    Log.d("TEST1PRVA22", UsingDeck.cardValue.size.toString())

                   // Log.d("Rand", random.toString())
                    binding.firstCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"

                    binding.firstSelector.isVisible = true
                    binding.secondSelector.isVisible = false
                    binding.thirdSelector.isVisible = false
                    binding.fourthSelector.isVisible = false
                   // Log.d("ID", UsingDeck.cardId.toString())
                    if (UsingDeck.cardValue[random] > random1Value) {
                       // Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random1]}")
                        positionCounter++
                        if (positionCounter == 1) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = true
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.secondSelector)
                        }
                        random1 = random
                        random1Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips++
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips++
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random1 = random
                        random1Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }
                }


                1 -> {
                    if(UsingDeck.cardId.size==0){
                        UsingDeck.refillCards()
                        Log.d("Us d", UsingDeck.cardValue.toString())
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }
                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
//                    Log.d("TEST2druga", UsingDeck.cardId.size.toString())
//                    Log.d("TESTdruga2222ZNACIZASIZE", UsingDeck.cardValue.size.toString())

                    binding.secondCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                   // Log.d("ID", UsingDeck.cardId.toString())
                    if (UsingDeck.cardValue[random] > random2Value) {
                       // Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random2]}")
                        positionCounter++
                        if (positionCounter == 2) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = true
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.thirdSelector)
                        }
                        random2 = random
                        random2Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                    UsingDeck.cardValue.removeAt(random)
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips = pOneSips + 2
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 2
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random2 = random
                        random2Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }
                }

                2 -> {
                    if(UsingDeck.cardId.size==0){
                        UsingDeck.refillCards()
                        Log.d("Us d", UsingDeck.cardValue.toString())
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }
                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
//                    Log.d("TEST3TRECA:", UsingDeck.cardId.size.toString())


                    binding.thirdCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                   // Log.d("ID", UsingDeck.cardId.toString())
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] > random3Value) {
                       // Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random3 iznosi ${UsingDeck.cardValue[random3]}")
                        positionCounter++
                        if (positionCounter == 3) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = true
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.fourthSelector)
                        }
                        random3 = random
                        random3Value = UsingDeck.cardValue[random]
                       UsingDeck.cardId.removeAt(random)
                    UsingDeck.cardValue.removeAt(random)
                        // MOZDA OVAJ RANDOM UTJECE NA  RANDOM KOJI SE KORISTI ZA UPSOREDBU A AKO GA SE MAKNE ONDA NESTANE I TAJ RANDOM UVJET ZA USPOREDBU VJEROJATNO TREBA STVORITI NOVU RAND VARIJABLU
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips = pOneSips + 3
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 3
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random3 = random
                        random3Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }

                }

                3 -> {
                    if(UsingDeck.cardId.size==0){
                        UsingDeck.refillCards()
                        Log.d("Us d", UsingDeck.cardValue.toString())
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }
                   // Log.d("ID", UsingDeck.cardId.toString())
                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
//                    Log.d("TEST4CETVRTA", UsingDeck.cardId.size.toString())

                    binding.fourthCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] > random4Value) {
                       // Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random4 iznosi ${UsingDeck.cardValue[random4]}")
                        positionCounter++
                        if (positionCounter == 4) {
                            SoundMethod.SoundPlayerNoLoop(this,R.raw.beep)
                            positionCounter = 0
                            binding.firstSelector.isVisible = true
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            UsingDeck.refillCards()

                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.firstCard)
                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.secondCard)
                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.thirdCard)
                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.fourthCard)

                            random1 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.firstCard.setImageResource(UsingDeck.cardId[random1])
                            random1Value=UsingDeck.cardValue[random1]
                            UsingDeck.cardId.removeAt(random1)
                            UsingDeck.cardValue.removeAt(random1)

                            random2 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.secondCard.setImageResource(UsingDeck.cardId[random2])
                            random2Value=UsingDeck.cardValue[random2]
                            UsingDeck.cardId.removeAt(random2)
                            UsingDeck.cardValue.removeAt(random2)

                            random3 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.thirdCard.setImageResource(UsingDeck.cardId[random3])
                            random3Value=UsingDeck.cardValue[random3]
                            UsingDeck.cardId.removeAt(random3)
                            UsingDeck.cardValue.removeAt(random3)

                            random4 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.fourthCard.setImageResource(UsingDeck.cardId[random4])
                            random4Value=UsingDeck.cardValue[random4]
                            UsingDeck.cardId.removeAt(random4)
                            UsingDeck.cardValue.removeAt(random4)

                            cardsDeck=28
                            binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)
                            Toast.makeText(this, "Igrač: ${currentPlayer[currentPlayerIndex].toString()} je izašao iz busa", Toast.LENGTH_LONG).show()
                            currentPlayerIndex++
                            if (currentPlayerIndex >= 2) {
                                currentPlayerIndex = 0
                            }
                            binding.playerTag.text = currentPlayer[currentPlayerIndex]
                            YoYo.with(Techniques.Shake).duration(250).repeat(2).playOn(binding.playerTag)
                            round++
                            binding.roundNumber.text = "Runda broj: ${round.toString()}"


                            if (playerOneName == currentPlayer[currentPlayerIndex]) {
                                pOneSips = pOneSips + 4
                                binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                            } else {
                                pTwoSips = pTwoSips + 4
                                binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                            }
                        }
                    }  else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips = pOneSips + 4
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 4
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random4 = random
                        random4Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }

                }
            }
        }

        binding.lowerButton.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.cardflip)
            when (positionCounter) {
                0 -> {
                    if(cardsDeck==0){
                        UsingDeck.refillCards()
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }
                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
                    binding.firstCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"

                    binding.firstSelector.isVisible = true
                    binding.secondSelector.isVisible = false
                    binding.thirdSelector.isVisible = false
                    binding.fourthSelector.isVisible = false

                    if (UsingDeck.cardValue[random] < random1Value) {
                       // Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random1]}")
                        positionCounter++
                        if (positionCounter == 1) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = true
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.secondSelector)
                        }
                        random1 = random
                        random1Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips++
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips++
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random1 = random
                        random1Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }
                }


                1 -> {
                    if(cardsDeck==0){
                        UsingDeck.refillCards()
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }
                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
                    binding.secondCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] < random2Value) {
//                        Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random2]}")
                        positionCounter++
                        if (positionCounter == 2) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = true
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.thirdSelector)
                        }
                        random2 = random
                        random2Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips = pOneSips + 2
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 2
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random2 = random
                        random2Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }
                }

                2 -> {
                    if(cardsDeck==0){
                        UsingDeck.refillCards()
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }
                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
                    binding.thirdCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] < random3Value) {
//                        Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random3 iznosi ${UsingDeck.cardValue[random3]}")
                        positionCounter++
                        if (positionCounter == 3) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = true
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.fourthSelector)
                        }
                        random3 = random
                        random3Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips = pOneSips + 3
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 3
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random3 = random
                        random3Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }

                }

                3 -> {
                    if(cardsDeck==0){
                        UsingDeck.refillCards()
                        cardsDeck=32
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips +=10
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips +=10
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                    }

                    var random = if(UsingDeck.cardId.size==0){
                        0
                    } else{
                        Random.nextInt(UsingDeck.cardId.size)
                    }
                    binding.fourthCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] < random4Value) {
                       // Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random4 iznosi ${UsingDeck.cardValue[random4]}")

                        positionCounter++
                        if (positionCounter == 4) {
                            SoundMethod.SoundPlayerNoLoop(this,R.raw.beep)
                            positionCounter = 0
                            binding.firstSelector.isVisible = true
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            UsingDeck.refillCards()

                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.firstCard)
                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.secondCard)
                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.thirdCard)
                            YoYo.with(Techniques.Landing).duration(1500).repeat(0).playOn(binding.fourthCard)

                            random1 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.firstCard.setImageResource(UsingDeck.cardId[random1])
                            random1Value=UsingDeck.cardValue[random1]
                            UsingDeck.cardId.removeAt(random1)
                            UsingDeck.cardValue.removeAt(random1)

                            random2 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.secondCard.setImageResource(UsingDeck.cardId[random2])
                            random2Value=UsingDeck.cardValue[random2]
                            UsingDeck.cardId.removeAt(random2)
                            UsingDeck.cardValue.removeAt(random2)

                            random3 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.thirdCard.setImageResource(UsingDeck.cardId[random3])
                            random3Value=UsingDeck.cardValue[random3]
                            UsingDeck.cardId.removeAt(random3)
                            UsingDeck.cardValue.removeAt(random3)

                            random4 = Random.nextInt(UsingDeck.cardId.size-1)
                            binding.fourthCard.setImageResource(UsingDeck.cardId[random4])
                            random4Value=UsingDeck.cardValue[random4]
                            UsingDeck.cardId.removeAt(random4)
                            UsingDeck.cardValue.removeAt(random4)

                            cardsDeck=28
                            binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"

                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)
                            Toast.makeText(this, "Igrač: ${currentPlayer[currentPlayerIndex].toString()}  je izašao iz busa", Toast.LENGTH_LONG).show()
                            currentPlayerIndex++
                            if (currentPlayerIndex >= 2) {
                                currentPlayerIndex = 0
                            }
                            binding.playerTag.text = currentPlayer[currentPlayerIndex]
                            YoYo.with(Techniques.Landing).duration(250).repeat(2).playOn(binding.playerTag)
                            round++
                            binding.roundNumber.text = "Runda broj:${round.toString()}"
                        }
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips = pOneSips + 4
                            binding.playerOneSips.text = "${playerOneName} broj gutljaja: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 4
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljaja: ${pTwoSips.toString()}"
                        }
                        random4 = random
                        random4Value = UsingDeck.cardValue[random]
                        UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)
                    }

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
                        SoundMethod.SoundPlayerNoLoop(this@GameActivity,R.raw.menu)
                        var intent = Intent(this@GameActivity , MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
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