package com.example.kronerapp.classes

import com.example.kronerapp.R

class Music {
    var musicId: MutableList<Int> = mutableListOf(R.raw.lone_djurdjevdan,
            R.raw.lone_od_kad_sam_se_rodio,
            R.raw.lone_samo_pijan_mogu,
            R.raw.lone_bizuterija
    )

    fun getRandomSong(): Int {
        val randIndex = (0..musicId.size-1).random()
        return musicId[randIndex]
    }
}