package com.qw.kronerapp.classes

import com.qw.kronerapp.R

class Music {
    var musicId: MutableList<Int> = mutableListOf(R.raw.lone_djurdjevdan,
            R.raw.lone_od_kad_sam_se_rodio,
            R.raw.lone_samo_pijan_mogu,
            R.raw.lone_bizuterija,
            R.raw.lone_vasko
    )

    fun getRandomSong(): Int {
        val randIndex = (0..musicId.size-1).random()
        return musicId[randIndex]
    }
}