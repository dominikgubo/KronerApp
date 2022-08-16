package com.example.kronerapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import androidx.core.view.isVisible
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.kronerapp.databinding.ActivityGameBinding
import com.example.kronerapp.databinding.ActivityMainBinding
import kotlin.random.Random
import android.util.Log
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.kronerapp.Card
import com.google.android.material.dialog.MaterialAlertDialogBuilder



class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val playerOneName = intent.getStringExtra("playerOneName")
        val playerTwoName = intent.getStringExtra("playerTwoName")
        var pOneSips = 0
        var pTwoSips = 0
        var round = 1
        var cardsDeck = 28
        var currentPlayer = arrayOf(playerOneName, playerTwoName)
        var currentPlayerIndex = 0

        binding.playerTag.text = currentPlayer[currentPlayerIndex]
        binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
        binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
        binding.roundNumber.text = "Runda broj: ${round.toString()}"
        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"

        var UsingDeck = Card()
        var random1 = Random.nextInt(UsingDeck.cardId.size)
        binding.firstCard.setImageResource(UsingDeck.cardId[random1])
        Log.d("UsingDeck test", UsingDeck.cardValue[random1].toString())
        UsingDeck.cardId.removeAt(random1)

        var random2 = Random.nextInt(UsingDeck.cardId.size)
        binding.secondCard.setImageResource(UsingDeck.cardId[random2])
        UsingDeck.cardId.removeAt(random2)

        var random3 = Random.nextInt(UsingDeck.cardId.size)
        binding.thirdCard.setImageResource(UsingDeck.cardId[random3])
        UsingDeck.cardId.removeAt(random3)

        var random4 = Random.nextInt(UsingDeck.cardId.size)
        binding.fourthCard.setImageResource(UsingDeck.cardId[random4])
        UsingDeck.cardId.removeAt(random4)

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
                MaterialAlertDialogBuilder(this).setTitle("Upozorenje")
                        .setMessage("Jel ste sigurni da želite predati igru? Ako predate [[ ${playerTwoName.toString()} ]] će pobijediti.").setNegativeButton("Nastavi", object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                Log.d("Continue", "Game continues.")
                            }
                        }).setPositiveButton("Predaj", object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                var intent = Intent(this@GameActivity , MainActivity::class.java)
                                startActivity(intent)
                            }
                        }).show()
            }
            else{
                MaterialAlertDialogBuilder(this).setTitle("Upozorenje")
                        .setMessage("Jel ste sigurni da želite predati igru? Ako predate [[ ${playerOneName.toString()} ]] će pobijediti.").setNegativeButton("Nastavi", object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                Log.d("Continue", "Game continues.")
                            }
                        }).setPositiveButton("Predaj", object : DialogInterface.OnClickListener{
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                var intent = Intent(this@GameActivity , MainActivity::class.java)
                                startActivity(intent)
                            }
                        }).show()
            }
        }

        binding.surrenderButton.setOnClickListener {
            openWarningDialog()
        }


        binding.higherButton.setOnClickListener {

            when (positionCounter) {
                0 -> {
                    var random = Random.nextInt(UsingDeck.cardId.size)
                    Log.d("Rand", random.toString())
                    binding.firstCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"

                    binding.firstSelector.isVisible = true
                    binding.secondSelector.isVisible = false
                    binding.thirdSelector.isVisible = false
                    binding.fourthSelector.isVisible = false

                    if (UsingDeck.cardValue[random] > UsingDeck.cardValue[random1]) {
                        Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random1]}")
                        positionCounter++
                        if (positionCounter == 1) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = true
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.secondSelector)
                        }
                        random1 = random
                        /*UsingDeck.cardId.removeAt(random)
                        UsingDeck.cardValue.removeAt(random)*/
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips++
                            binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                        } else {
                            pTwoSips++
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                        }
                        random1 = random
                    }
                }


                1 -> {
                    var random = Random.nextInt(UsingDeck.cardId.size)
                    binding.secondCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] > UsingDeck.cardValue[random2]) {
                        Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random2]}")
                        positionCounter++
                        if (positionCounter == 2) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = true
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.thirdSelector)
                        }
                        random2 = random
                        /*UsingDeck.cardId.removeAt(random)
                    UsingDeck.cardValue.removeAt(random)*/
                    } else {
                        positionCounter = 0

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false
                        YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                        if (playerOneName == currentPlayer[currentPlayerIndex]) {
                            pOneSips = pOneSips + 2
                            binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 2
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                        }
                        random2 = random
                    }
                }

                2 -> {
                    var random = Random.nextInt(UsingDeck.cardId.size)
                    binding.thirdCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] > UsingDeck.cardValue[random3]) {
                        Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random3 iznosi ${UsingDeck.cardValue[random3]}")
                        positionCounter++
                        if (positionCounter == 3) {
                            binding.firstSelector.isVisible = false
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = true
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.fourthSelector)
                        }
                        random3 = random
                        /*UsingDeck.cardId.removeAt(random)
                    UsingDeck.cardValue.removeAt(random)*/
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
                            binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 3
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                        }
                        random3 = random
                    }

                }

                3 -> {
                    var random = Random.nextInt(UsingDeck.cardId.size)
                    binding.fourthCard.setImageResource(UsingDeck.cardId[random])
                    cardsDeck--
                    binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                    if (UsingDeck.cardValue[random] > UsingDeck.cardValue[random4]) {
                        Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random4 iznosi ${UsingDeck.cardValue[random4]}")
                        positionCounter++
                        if (positionCounter == 4) {
                            positionCounter = 0
                            binding.firstSelector.isVisible = true
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)
                            Toast.makeText(this, "Igrač [[ ${currentPlayer[currentPlayerIndex].toString()} ]] je izašao iz busa", Toast.LENGTH_LONG).show()
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
                                binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                            } else {
                                pTwoSips = pTwoSips + 4
                                binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                            }
                            random4 = random
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
                            binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                        } else {
                            pTwoSips = pTwoSips + 4
                            binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                        }
                        random4 = random
                    }

                }
            }

            binding.lowerButton.setOnClickListener {

                when (positionCounter) {
                    0 -> {
                        var random = Random.nextInt(UsingDeck.cardId.size)

                        binding.firstCard.setImageResource(UsingDeck.cardId[random])
                        cardsDeck--
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"

                        binding.firstSelector.isVisible = true
                        binding.secondSelector.isVisible = false
                        binding.thirdSelector.isVisible = false
                        binding.fourthSelector.isVisible = false

                        if (UsingDeck.cardValue[random] < UsingDeck.cardValue[random1]) {
                            Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random1]}")
                            positionCounter++
                            if (positionCounter == 1) {
                                binding.firstSelector.isVisible = false
                                binding.secondSelector.isVisible = true
                                binding.thirdSelector.isVisible = false
                                binding.fourthSelector.isVisible = false
                                YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.secondSelector)
                            }
                            random1 = random
                            /*UsingDeck.cardId.removeAt(random)
                    UsingDeck.cardValue.removeAt(random)*/
                        } else {
                            positionCounter = 0

                            binding.firstSelector.isVisible = true
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                            if (playerOneName == currentPlayer[currentPlayerIndex]) {
                                pOneSips++
                                binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                            } else {
                                pTwoSips++
                                binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                            }
                            random1 = random
                        }
                    }


                    1 -> {
                        var random = Random.nextInt(UsingDeck.cardId.size)
                        binding.secondCard.setImageResource(UsingDeck.cardId[random])
                        cardsDeck--
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (UsingDeck.cardValue[random] < UsingDeck.cardValue[random2]) {
                            Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random2 iznosi ${UsingDeck.cardValue[random2]}")
                            positionCounter++
                            if (positionCounter == 2) {
                                binding.firstSelector.isVisible = false
                                binding.secondSelector.isVisible = false
                                binding.thirdSelector.isVisible = true
                                binding.fourthSelector.isVisible = false
                                YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.thirdSelector)
                            }
                            random2 = random
                            /*UsingDeck.cardId.removeAt(random)
                    UsingDeck.cardValue.removeAt(random)*/
                        } else {
                            positionCounter = 0

                            binding.firstSelector.isVisible = true
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                            if (playerOneName == currentPlayer[currentPlayerIndex]) {
                                pOneSips = pOneSips + 2
                                binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                            } else {
                                pTwoSips = pTwoSips + 2
                                binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                            }
                            random2 = random
                        }
                    }

                    2 -> {
                        var random = Random.nextInt(UsingDeck.cardId.size)
                        binding.thirdCard.setImageResource(UsingDeck.cardId[random])
                        cardsDeck--
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (UsingDeck.cardValue[random] < UsingDeck.cardValue[random3]) {
                            Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random3 iznosi ${UsingDeck.cardValue[random3]}")
                            positionCounter++
                            if (positionCounter == 3) {
                                binding.firstSelector.isVisible = false
                                binding.secondSelector.isVisible = false
                                binding.thirdSelector.isVisible = false
                                binding.fourthSelector.isVisible = true
                                YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.fourthSelector)
                            }
                            random3 = random
                            /*UsingDeck.cardId.removeAt(random)
                    UsingDeck.cardValue.removeAt(random)*/
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
                                binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                            } else {
                                pTwoSips = pTwoSips + 3
                                binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                            }
                            random3 = random
                        }

                    }

                    3 -> {
                        var random = Random.nextInt(UsingDeck.cardId.size)
                        binding.fourthCard.setImageResource(UsingDeck.cardId[random])
                        cardsDeck--
                        binding.cardsDeck.text = "Karti u špilu: ${cardsDeck.toString()}"
                        if (UsingDeck.cardValue[random] < UsingDeck.cardValue[random4]) {
                            Log.d("Values", "Random iznosi ${UsingDeck.cardValue[random]}, Random4 iznosi ${UsingDeck.cardValue[random4]}")

                            positionCounter++
                            if (positionCounter == 4) {
                                positionCounter = 0
                                binding.firstSelector.isVisible = true
                                binding.secondSelector.isVisible = false
                                binding.thirdSelector.isVisible = false
                                binding.fourthSelector.isVisible = false
                                YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)
                                Toast.makeText(this, "Igrač [[ ${currentPlayer[currentPlayerIndex].toString()} ]] je izašao iz busa", Toast.LENGTH_LONG).show()
                                currentPlayerIndex++
                                if (currentPlayerIndex >= 2) {
                                    currentPlayerIndex = 0
                                }
                                binding.playerTag.text = currentPlayer[currentPlayerIndex]
                                YoYo.with(Techniques.Landing).duration(250).repeat(2).playOn(binding.playerTag)
                                round++
                                binding.roundNumber.text = "Runda broj:${round.toString()}"
                            }
                            random4 = random
                        } else {
                            positionCounter = 0

                            binding.firstSelector.isVisible = true
                            binding.secondSelector.isVisible = false
                            binding.thirdSelector.isVisible = false
                            binding.fourthSelector.isVisible = false
                            YoYo.with(Techniques.Bounce).duration(1500).repeat(Animation.INFINITE).playOn(binding.firstSelector)

                            if (playerOneName == currentPlayer[currentPlayerIndex]) {
                                pOneSips = pOneSips + 4
                                binding.playerOneSips.text = "${playerOneName} broj gutljajeva: ${pOneSips.toString()}"
                            } else {
                                pTwoSips = pTwoSips + 4
                                binding.playerTwoSips.text = "${playerTwoName} broj gutljajeva: ${pTwoSips.toString()}"
                            }
                            random4 = random
                        }

                    }

                }
            }


        }
    }

        var usedCards: MutableList<Int> = mutableListOf()




}