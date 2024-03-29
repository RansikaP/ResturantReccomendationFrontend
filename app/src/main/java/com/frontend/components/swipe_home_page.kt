package com.frontend.components

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.frontend.R
class swipe_home_page : AppCompatActivity() {
    private lateinit var imageView: ImageView

    // This method is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view of the activity to the specified layout resource.
        setContentView(R.layout.activity_home_page)
//
//         Initialize the imageView variable with the ImageView from the layout.
//        imageView = findViewById(R.id.imageView)
    }

    // Function to handle the click event of the "Like" button.
    fun onLikeButtonClick(view: View) {
        // Handle like button click by displaying a toast message.
        showToast("Liked!")
    }

    // Function to handle the click event of the "Dislike" button.
    fun onDislikeButtonClick(view: View) {
        // Handle dislike button click by displaying a toast message.
        showToast("Disliked!")
    }

    // Function to display a toast message with the specified text.
    private fun showToast(message: String) {
        // Create a Toast message with the given text and show it.
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}