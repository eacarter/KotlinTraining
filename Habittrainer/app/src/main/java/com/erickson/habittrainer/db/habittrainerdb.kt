package com.erickson.habittrainer.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class habittrainerdb(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATBASE_VERSION) {

    private val SQL_CREATE_ENTRIES = "CREATE TABLE ${HabitEntry.TABLE_NAME}(" +
            "${HabitEntry.ID} INTEGER PRIMARY KEY, " +
            "${HabitEntry.TITLE_COL} TEXT," +
            "${HabitEntry.DESC_COL} TEXT," +
            "${HabitEntry.IMG_COL} BLOB" +
            ")"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${HabitEntry.TABLE_NAME} "

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}