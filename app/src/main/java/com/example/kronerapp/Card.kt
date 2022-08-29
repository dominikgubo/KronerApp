package com.example.kronerapp

import java.security.CodeSource

class Card {
    var cardId: MutableList<Int> = mutableListOf(R.drawable.sas,
            R.drawable.bas,
            R.drawable.zas,
            R.drawable.las,
            R.drawable.skralj,
            R.drawable.lkralj,
            R.drawable.zkralj,
            R.drawable.bkralj,
            R.drawable.sdama,
            R.drawable.bdama,
            R.drawable.ldama,
            R.drawable.zdama,
            R.drawable.sdecko,
            R.drawable.bdecko,
            R.drawable.ldecko,
            R.drawable.zdecko,
            R.drawable.s10,
            R.drawable.b10,
            R.drawable.z10,
            R.drawable.l10,
            R.drawable.s9,
            R.drawable.z9,
            R.drawable.l9,
            R.drawable.b9,
            R.drawable.s8,
            R.drawable.l8,
            R.drawable.b8,
            R.drawable.z8,
            R.drawable.s7,
            R.drawable.b7,
            R.drawable.z7,
            R.drawable.l7)

    var cardId2: MutableList<Int> = mutableListOf(R.drawable.sas,
            R.drawable.bas,
            R.drawable.zas,
            R.drawable.las,
            R.drawable.skralj,
            R.drawable.lkralj,
            R.drawable.zkralj,
            R.drawable.bkralj,
            R.drawable.sdama,
            R.drawable.bdama,
            R.drawable.ldama,
            R.drawable.zdama,
            R.drawable.sdecko,
            R.drawable.bdecko,
            R.drawable.ldecko,
            R.drawable.zdecko,
            R.drawable.s10,
            R.drawable.b10,
            R.drawable.z10,
            R.drawable.l10,
            R.drawable.s9,
            R.drawable.z9,
            R.drawable.l9,
            R.drawable.b9,
            R.drawable.s8,
            R.drawable.l8,
            R.drawable.b8,
            R.drawable.z8,
            R.drawable.s7,
            R.drawable.b7,
            R.drawable.z7,
            R.drawable.l7)

    var cardValue: MutableList<Int> = mutableListOf(14, 14, 14, 14, 13, 13, 13, 13, 12, 12, 12, 12, 11, 11, 11, 11, 10, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7)
    var cardValue2: MutableList<Int> = mutableListOf(14, 14, 14, 14, 13, 13, 13, 13, 12, 12, 12, 12, 11, 11, 11, 11, 10, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7)

    fun refillCards(){
        cardId = ArrayList(cardId2)
        cardValue = ArrayList(cardValue2)
    }
}