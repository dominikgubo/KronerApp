package com.qw.kronerapp

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.qw.kronerapp.classes.AppLifeCycleHelper
import com.qw.kronerapp.classes.SoundMethod
import com.qw.kronerapp.databinding.ActivityModeBinding
import com.qw.kronerapp.loading.LoadingKingActivity


class ModeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModeBinding
    var flag = false

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerActivityLifecycleCallbacks(AppLifeCycleHelper())


        binding = ActivityModeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.autobusStart.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
            setFragment(PlayersFragment())
        }

        binding.neverStart.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
            /*var intent = Intent(this, NeverHaveIEverSelectActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()*/
            val toast = Toast.makeText(applicationContext, "[NEVER HAVE I EVER] Trenutno isključen zbog bugova.", Toast.LENGTH_SHORT)
            toast.show()
        }

        binding.kingStart.setOnClickListener {
            SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
            var intent = Intent(this, LoadingKingActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }


        YoYo.with(Techniques.Shake).duration(2500).repeat(Animation.INFINITE).playOn(binding.gameTitle)
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        if(flag==false){
            fragmentTransaction.commit()
            binding.neverStart.visibility = View.GONE;
            binding.kingStart.visibility = View.GONE;
        } else {
            fragmentTransaction.remove(fragment)
            fragmentTransaction.commit()
            binding.neverStart.visibility = View.VISIBLE;
            binding.kingStart.visibility = View.VISIBLE;
        }
        flag = !flag
    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        SoundMethod.SoundPlayerNoLoop(this, R.raw.menu)
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

    /*override fun onPause() {
        super.onPause()
        if (MusicMethod.player != null && (!binding.kingStart.isPressed || !binding.autobusStart.isPressed || !binding.neverStart.isPressed )) {
            MusicMethod.player.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (MusicMethod.player != null) {
            MusicMethod.player.start()
        }
    } */

}