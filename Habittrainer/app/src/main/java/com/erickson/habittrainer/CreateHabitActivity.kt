package com.erickson.habittrainer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_create_habit.*
import kotlinx.android.synthetic.main.activity_create_habit.view.*
import java.io.IOException

class CreateHabitActivity : AppCompatActivity() {

    private val CHOOSE_IMAGE_REQUEST = 1
    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_habit)
    }

    fun chooseImage(view: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        var chooser = Intent.createChooser(intent, "Choose image for habit")
        startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)
        Log.d(this.localClassName, "Intent to choose image sent...")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == CHOOSE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
            && data != null && data.data != null){
            Log.d(this.localClassName, "Image Chosen")

            val bitmap = tryReadBitmap(data.data!!)

            bitmap?.let {
                imageBitmap = bitmap
                card2.visibility = View.VISIBLE
                preview_image.setImageBitmap(bitmap)
                Log.d(this.localClassName, "read image bitmap, updated image view")
            }
        }
    }

    private fun tryReadBitmap(data: Uri): Bitmap? {
        return try {
            MediaStore.Images.Media.getBitmap(contentResolver, data)
        }
        catch (e:IOException){
            e.printStackTrace()
            null
        }
    }

    fun storeHabit(view: View) {
        if (view.title.text.toString().isBlank() || view.description.toString().isBlank()){
            displayErrorMessage("Missing title or Description")
            return
        }
        else if(imageBitmap == null){
            displayErrorMessage("Missing image")
            return
        }
        error_text.visibility = View.GONE
    }

    private fun displayErrorMessage(text: String) {
        error_text.visibility = View.VISIBLE
        error_text.text = text
    }
}
