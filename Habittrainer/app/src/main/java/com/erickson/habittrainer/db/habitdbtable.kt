package com.erickson.habittrainer.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.erickson.habittrainer.data.Habit
import java.io.ByteArrayOutputStream

class habitdbtable(context: Context){

    private val dbhelper = habittrainerdb(context)

    fun store(habit: Habit): Long {
        val db = dbhelper.writableDatabase
        val values = ContentValues()
        with(values) {
            put(HabitEntry.TITLE_COL, habit.title)
            put(HabitEntry.DESC_COL, habit.descrip)
            put(HabitEntry.IMG_COL, toByteArray(habit.image))
        }
        val id = db.transaction {
            insert(HabitEntry.TABLE_NAME, null, values)
        }

        return id
    }

    fun readAll(): List<Habit>{
        val cols = arrayOf(HabitEntry.ID, HabitEntry.TITLE_COL, HabitEntry.DESC_COL, HabitEntry.IMG_COL)

        val order = "${HabitEntry.ID} ASC"

        val db = dbhelper.readableDatabase
        val cursor = db.query(HabitEntry.TABLE_NAME, cols, null, null, null,null, order)
        val habits = mutableListOf<Habit>()

        while (cursor.moveToNext()){
            val title = cursor.getString(cursor.getColumnIndex(HabitEntry.TITLE_COL))
            val desc = cursor.getString(cursor.getColumnIndex(HabitEntry.DESC_COL))
            val byteArray = cursor.getBlob(cursor.getColumnIndex(HabitEntry.IMG_COL))
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            habits.add(Habit(title, desc, bitmap))
        }

        return habits
    }

    private fun toByteArray(image: Bitmap): ByteArray{
        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }
}

private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T{
    beginTransaction()
    val result = try {
        val returnValue = function()
        setTransactionSuccessful()
        returnValue
    } finally {
        endTransaction()
    }
    close()

    return result
}