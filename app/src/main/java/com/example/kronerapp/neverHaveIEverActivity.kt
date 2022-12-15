package com.example.kronerapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.kronerapp.classes.EmojiGifs
import com.example.kronerapp.classes.MusicMethod
import com.example.kronerapp.classes.SoundMethod
import com.example.kronerapp.databinding.ActivityNeverHaveIEverBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.random.Random

class neverHaveIEverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNeverHaveIEverBinding

    var kronerDbRef = FirebaseDatabase.getInstance().getReference().child("Kroner")

    val database = Firebase.database




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityNeverHaveIEverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var questionList: MutableList<String> = mutableListOf()

        var emojiList = EmojiGifs()

        var randomIndex = Random.nextInt(emojiList.emojiId.size-1)
        binding.gifImageView.setImageResource(emojiList.emojiId[randomIndex])
        emojiList.emojiId.removeAt(randomIndex)
        var counter = 1
        var modeCounter = 0
        var index = 1
        var index2 = 1
        var index3 = 1
        var index4 = 1

        val checkboxStudentValue = intent.getStringExtra("StudentCheckbox")
        val checkboxDriverValue = intent.getStringExtra("DriverCheckbox")
        val checkboxAdultValue = intent.getStringExtra("AdultCheckbox")
        
        MusicMethod.player.start()


        // 151 Questions
        for(i in 0..150) {
            var selector = "q$index"
            var myRef = database.getReference().child("Questions").child(selector)
            index++


            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue().toString()
                    //Log.d("FB", "Value is: $value")
                    questionList.add(value)
                    if(i==149){
                        binding.questionLabel.text = questionList[randomIndex].toString()
                        binding.questionNumberLabel.text = counter.toString()+"/"+(questionList.size).toString()
                        questionList.remove(questionList[randomIndex])
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    //Log.w("FB F", "Failed to read value.", error.toException())
                }
            })


        }

        if (checkboxStudentValue=="true"){
            modeCounter++
            for(i in 0..49){
                var qselector = "q$index2"
                var myRefStudent = database.getReference().child("StudentQuestions").child(qselector)
                index2++
                myRefStudent.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val value = dataSnapshot.getValue().toString()
                        //Log.d("FBI", "Value is: $value")
                        questionList.add(value)
                        binding.questionLabel.text = questionList[randomIndex].toString()
                        binding.questionNumberLabel.text = counter.toString()+"/"+(questionList.size).toString()
                    }
                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        //Log.w("FB F", "Failed to read value.", error.toException())
                    }
                })
            }
        }

        if (checkboxDriverValue=="true"){
            modeCounter++
            for(i in 0..49){
                var qselector = "q$index3"
                var myRefDriver = database.getReference().child("DriverQuestions").child(qselector)
                index3++
                myRefDriver.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val valueDriver = dataSnapshot.getValue().toString()
                        //Log.d("FBQ", "Value is: $valueDriver")
                        questionList.add(valueDriver)
                        binding.questionLabel.text = questionList[randomIndex].toString()
                        binding.questionNumberLabel.text = counter.toString()+"/"+(questionList.size).toString()
                    }
                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        //Log.w("FB F", "Failed to read value.", error.toException())
                    }
                })
            }
        }

        if (checkboxAdultValue=="true"){
            modeCounter++
            for(i in 0..49){
                var qselector = "q$index4"
                var myRefAdult = database.getReference().child("18Questions").child(qselector)
                index4++
                myRefAdult.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val valueAdult = dataSnapshot.getValue().toString()
                        //Log.d("FBW", "Value is: $valueAdult")
                        questionList.add(valueAdult)
                        binding.questionLabel.text = questionList[randomIndex].toString()
                        binding.questionNumberLabel.text = counter.toString()+"/"+(questionList.size).toString()
                    }
                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        //Log.w("FB F", "Failed to read value.", error.toException())
                    }
                })
            }
        }


        binding.gifImageView.setOnClickListener{
            SoundMethod.SoundPlayerNoLoop(this,R.raw.menu)
            var sum = counter + questionList.size-1
            counter++

            if(counter==151 && modeCounter==0){
                binding.neverLabel.text = "Igra je gotova"
                binding.gifImageView.setImageResource(R.drawable.g_end)
                binding.questionLabel.text = "Sva pitanja su postavljena"
                binding.questionNumberLabel.visibility = View.INVISIBLE
                binding.nextQuestionButton.text = "NAZAD"
                var intent = Intent(this,MainActivity::class.java)
                binding.nextQuestionButton.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
                binding.gifImageView.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            } else if(counter==151+(modeCounter*50) && modeCounter!=0){
                binding.neverLabel.text = "Igra je gotova"
                binding.gifImageView.setImageResource(R.drawable.g_end)
                binding.questionLabel.text = "Sva pitanja su postavljena"
                binding.questionNumberLabel.visibility = View.INVISIBLE
                binding.nextQuestionButton.text = "NAZAD"
                var intent = Intent(this,MainActivity::class.java)
                binding.nextQuestionButton.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
                binding.gifImageView.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
            else{
                if(emojiList.emojiId.size==1){
                    emojiList.resetEmojis()
                }
                var random = Random.nextInt(emojiList.emojiId.size-1)
                binding.gifImageView.setImageResource(emojiList.emojiId[random])
                emojiList.emojiId.removeAt(random)
                //Log.e("Var", "Velicina: "+questionList.size)
                var randomQuestion = Random.nextInt(questionList.size-1)
                binding.questionLabel.text = questionList[randomQuestion].toString()

                //Log.e("AAAC", questionList[randomQuestion])
                questionList.remove(questionList[randomQuestion])
                //Log.e("AAA",randomQuestion.toString())
                //Log.e("AAA",questionList.toString())
                binding.questionNumberLabel.text = counter.toString()+"/"+sum.toString()
            }
        }


        binding.nextQuestionButton.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this,R.raw.menu)
            var sum = counter + questionList.size-1
            counter++

            if(counter==151 && modeCounter==0){
                binding.neverLabel.text = "Igra je gotova"
                binding.gifImageView.setImageResource(R.drawable.g_end)
                binding.questionLabel.text = "Sva pitanja su postavljena"
                binding.questionNumberLabel.visibility = View.INVISIBLE
                binding.nextQuestionButton.text = "NAZAD"
                var intent = Intent(this,MainActivity::class.java)
                binding.nextQuestionButton.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
                binding.gifImageView.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            } else if(counter==151+(modeCounter*50) && modeCounter!=0){
                binding.neverLabel.text = "Igra je gotova"
                binding.gifImageView.setImageResource(R.drawable.g_end)
                binding.questionLabel.text = "Sva pitanja su postavljena"
                binding.questionNumberLabel.visibility = View.INVISIBLE
                binding.nextQuestionButton.text = "NAZAD"
                var intent = Intent(this,MainActivity::class.java)
                binding.nextQuestionButton.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
                binding.gifImageView.setOnClickListener {
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }  else{
                if(emojiList.emojiId.size==1){
                    emojiList.resetEmojis()
                }
                var random = Random.nextInt(emojiList.emojiId.size-1)
                binding.gifImageView.setImageResource(emojiList.emojiId[random])
                emojiList.emojiId.removeAt(random)
                //Log.e("Var", "Velicina: "+questionList.size)
                var randomQuestion = Random.nextInt(questionList.size-1)
                binding.questionLabel.text = questionList[randomQuestion].toString()

                //Log.e("AAAC", questionList[randomQuestion])
                questionList.remove(questionList[randomQuestion])
                //Log.e("AAA",randomQuestion.toString())
                //Log.e("AAA",questionList.toString())
                binding.questionNumberLabel.text = counter.toString()+"/"+sum.toString()
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
                        SoundMethod.SoundPlayerNoLoop(this@neverHaveIEverActivity,R.raw.menu)
                        var intent = Intent(this@neverHaveIEverActivity , MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(android.R.anim.fade_in , android.R.anim.fade_out)
                        finish()
                    }
                }).show()
    }

    /*override fun onPause() {
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
    }*/
}