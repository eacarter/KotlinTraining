package com.erickson.habittrainer.data

import com.erickson.habittrainer.R

data class Habit( val title: String, val descrip: String, val image: Int)

fun getSampleHabit(): List<Habit>{
    return listOf(
        Habit(
            "Drink a glass of water",
            "A refreshing glass of water gets you hydrated",
            R.drawable.water
        ),
        Habit(
            "Go for a walk",
            "A nice walk in the sun gets you ready for the day ahead",
            R.drawable.walk
        )
    )

}