package com.erickson.habittrainer.db

import android.provider.BaseColumns

val DATABASE_NAME = "habittrainer.db"
val DATBASE_VERSION = 10

object HabitEntry: BaseColumns{
    val TABLE_NAME = "habit"
    val ID = "id"
    val TITLE_COL = "title"
    val DESC_COL = "description"
    val IMG_COL = "image"
}