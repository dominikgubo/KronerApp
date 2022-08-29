package com.example.kronerapp

import java.text.SimpleDateFormat
import java.util.*

class GameStats {
    var playerOneSips: String = "1"
    var playerTwoSips: String = "1"
    var winner: String = "Random"
    var time: String = "Time"
    var round: String = "Rounds"

    constructor(playerOneSips: String, playerTwoSips: String, winner: String, time: String, round: String){
        this.playerOneSips = playerOneSips
        this.playerTwoSips = playerTwoSips
        this.winner = winner
        this.round = round
        this.time = time
    }


}