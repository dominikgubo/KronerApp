package com.example.kronerapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)

        val preferences = view.context.getSharedPreferences("KronerStats", 0)
        var winners = preferences.getStringSet("winner",null)
        var playerOneSips = preferences.getStringSet("playerOneSips", null)
        var playerTwoSips = preferences.getStringSet("playerTwoSips", null)
        var rounds = preferences.getStringSet("round", null)
        var time = preferences.getStringSet("time", null)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        //holder.winnerText.text = winners[]
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var winnerText: TextView
        var p1sipsText: TextView
        var p2sipsText: TextView
        var timeText: TextView
        var roundText: TextView

        init {
            winnerText = itemView.findViewById(R.id.winnerText)
            p1sipsText = itemView.findViewById(R.id.playerOneSipsText)
            p2sipsText = itemView.findViewById(R.id.playerTwoSipsText)
            roundText = itemView.findViewById(R.id.roundNumberText)
            timeText = itemView.findViewById(R.id.timeText)
        }
    }
}