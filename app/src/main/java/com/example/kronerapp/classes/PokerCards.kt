package com.example.kronerapp.classes

import com.example.kronerapp.R

class PokerCards {
    var cardId: MutableList<Int> = mutableListOf(
        R.drawable.two_of_clubs,
        R.drawable.two_of_diamonds,
        R.drawable.two_of_hearts,
        R.drawable.two_of_spades,
        R.drawable.three_of_clubs,
        R.drawable.three_of_diamonds,
        R.drawable.three_of_hearts,
        R.drawable.three_of_spades,
        R.drawable.fo_of_clubs,
        R.drawable.fo_of_diamonds,
        R.drawable.fo_of_hearts,
        R.drawable.fo_of_spades,
        R.drawable.fi_of_clubs,
        R.drawable.fi_of_diamonds,
        R.drawable.fi_of_hearts,
        R.drawable.fi_of_spades,
        R.drawable.si_of_clubs,
        R.drawable.si_of_diamonds,
        R.drawable.si_of_hearts,
        R.drawable.si_of_spades,
        R.drawable.se_of_clubs,
        R.drawable.se_of_diamonds,
        R.drawable.se_of_hearts,
        R.drawable.se_of_spades,
        R.drawable.ei_of_clubs,
        R.drawable.ei_of_diamonds,
        R.drawable.ei_of_hearts,
        R.drawable.ei_of_spades,
        R.drawable.ni_of_clubs,
        R.drawable.ni_of_diamonds,
        R.drawable.ni_of_hearts,
        R.drawable.ni_of_spades,
        R.drawable.ten_of_clubs,
        R.drawable.ten_of_diamonds,
        R.drawable.ten_of_hearts,
        R.drawable.ten_of_spades,
        R.drawable.jack_of_clubs2,
        R.drawable.jack_of_diamonds2,
        R.drawable.jack_of_hearts2,
        R.drawable.jack_of_spades2,
        R.drawable.queen_of_clubs2,
        R.drawable.queen_of_diamonds2,
        R.drawable.queen_of_hearts2,
        R.drawable.queen_of_spades2,
        R.drawable.king_of_clubs2,
        R.drawable.king_of_diamonds2,
        R.drawable.king_of_hearts2,
        R.drawable.king_of_spades2,
        R.drawable.ace_of_clubs,
        R.drawable.ace_of_diamonds,
        R.drawable.ace_of_hearts,
        R.drawable.ace_of_spades2)

    var cardValue: MutableList<Int> = mutableListOf(
        2,
        2,
        2,
        2,
        3,
        3,
        3,
        3,
        4,
        4,
        4,
        4,
        5,
        5,
        5,
        5,
        6,
        6,
        6,
        6,
        7,
        7,
        7,
        7,
        8,
        8,
        8,
        8,
        9,
        9,
        9,
        9,
        10,
        10,
        10,
        10,
        11,
        11,
        11,
        11,
        12,
        12,
        12,
        12,
        13,
        13,
        13,
        13,
        14,
        14,
        14,
        14)

    var cardId2: MutableList<Int> = mutableListOf(
            R.drawable.two_of_clubs,
            R.drawable.two_of_diamonds,
            R.drawable.two_of_hearts,
            R.drawable.two_of_spades,
            R.drawable.three_of_clubs,
            R.drawable.three_of_diamonds,
            R.drawable.three_of_hearts,
            R.drawable.three_of_spades,
            R.drawable.fo_of_clubs,
            R.drawable.fo_of_diamonds,
            R.drawable.fo_of_hearts,
            R.drawable.fo_of_spades,
            R.drawable.fi_of_clubs,
            R.drawable.fi_of_diamonds,
            R.drawable.fi_of_hearts,
            R.drawable.fi_of_spades,
            R.drawable.si_of_clubs,
            R.drawable.si_of_diamonds,
            R.drawable.si_of_hearts,
            R.drawable.si_of_spades,
            R.drawable.se_of_clubs,
            R.drawable.se_of_diamonds,
            R.drawable.se_of_hearts,
            R.drawable.se_of_spades,
            R.drawable.ei_of_clubs,
            R.drawable.ei_of_diamonds,
            R.drawable.ei_of_hearts,
            R.drawable.ei_of_spades,
            R.drawable.ni_of_clubs,
            R.drawable.ni_of_diamonds,
            R.drawable.ni_of_hearts,
            R.drawable.ni_of_spades,
            R.drawable.ten_of_clubs,
            R.drawable.ten_of_diamonds,
            R.drawable.ten_of_hearts,
            R.drawable.ten_of_spades,
            R.drawable.jack_of_clubs2,
            R.drawable.jack_of_diamonds2,
            R.drawable.jack_of_hearts2,
            R.drawable.jack_of_spades2,
            R.drawable.queen_of_clubs2,
            R.drawable.queen_of_diamonds2,
            R.drawable.queen_of_hearts2,
            R.drawable.queen_of_spades2,
            R.drawable.king_of_clubs2,
            R.drawable.king_of_diamonds2,
            R.drawable.king_of_hearts2,
            R.drawable.king_of_spades2,
            R.drawable.ace_of_clubs,
            R.drawable.ace_of_diamonds,
            R.drawable.ace_of_hearts,
            R.drawable.ace_of_spades2)

    var cardValue2: MutableList<Int> = mutableListOf(
            2,
            2,
            2,
            2,
            3,
            3,
            3,
            3,
            4,
            4,
            4,
            4,
            5,
            5,
            5,
            5,
            6,
            6,
            6,
            6,
            7,
            7,
            7,
            7,
            8,
            8,
            8,
            8,
            9,
            9,
            9,
            9,
            10,
            10,
            10,
            10,
            11,
            11,
            11,
            11,
            12,
            12,
            12,
            12,
            13,
            13,
            13,
            13,
            14,
            14,
            14,
            14)

    fun resetCards(){
        cardId = ArrayList(cardId2)
        cardValue = ArrayList(cardValue2)
    }
}