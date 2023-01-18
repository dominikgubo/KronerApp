package com.qw.kronerapp.classes

import com.qw.kronerapp.R

class EmojiGifs {
    var emojiId: MutableList<Int> = mutableListOf(
            R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4, R.drawable.g5, R.drawable.g6,
            R.drawable.g7, R.drawable.g8, R.drawable.g9, R.drawable.g10, R.drawable.g11, R.drawable.g12,
            R.drawable.g13, R.drawable.g14, R.drawable.g15, R.drawable.g16, R.drawable.g17, R.drawable.g18,
            R.drawable.g14, R.drawable.g15, R.drawable.g16, R.drawable.g17, R.drawable.g18, R.drawable.g19,
            R.drawable.g20, R.drawable.g21, R.drawable.g22, R.drawable.g23, R.drawable.g24, R.drawable.g25,
            R.drawable.g26, R.drawable.g27, R.drawable.g28, R.drawable.g29, R.drawable.g30, R.drawable.g31,
            R.drawable.g32, R.drawable.g33, R.drawable.g34, R.drawable.g35, R.drawable.g36, R.drawable.g37,
            R.drawable.g38, R.drawable.g39, R.drawable.g40, R.drawable.g41, R.drawable.g42, R.drawable.g43,
            R.drawable.g44, R.drawable.g45, R.drawable.g46, R.drawable.g47)

    var emojiId2: MutableList<Int> = mutableListOf(
            R.drawable.g1, R.drawable.g2, R.drawable.g3, R.drawable.g4, R.drawable.g5, R.drawable.g6,
            R.drawable.g7, R.drawable.g8, R.drawable.g9, R.drawable.g10, R.drawable.g11, R.drawable.g12,
            R.drawable.g13, R.drawable.g14, R.drawable.g15, R.drawable.g16, R.drawable.g17, R.drawable.g18,
            R.drawable.g14, R.drawable.g15, R.drawable.g16, R.drawable.g17, R.drawable.g18, R.drawable.g19,
            R.drawable.g20, R.drawable.g21, R.drawable.g22, R.drawable.g23, R.drawable.g24, R.drawable.g25,
            R.drawable.g26, R.drawable.g27, R.drawable.g28, R.drawable.g29, R.drawable.g30, R.drawable.g31,
            R.drawable.g32, R.drawable.g33, R.drawable.g34, R.drawable.g35, R.drawable.g36, R.drawable.g37,
            R.drawable.g38, R.drawable.g39, R.drawable.g40, R.drawable.g41, R.drawable.g42, R.drawable.g43,
            R.drawable.g44, R.drawable.g45, R.drawable.g46, R.drawable.g47)


    fun resetEmojis(){
        emojiId = ArrayList(emojiId2)
    }

}