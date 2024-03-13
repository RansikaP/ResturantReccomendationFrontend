package com.frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.frontend.ui.theme.ResturantReccomendationFrontendTheme
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    // This method is called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view of the activity to the specified layout resource.
        setContentView(R.layout.activity_main)

        // Initialize the imageView variable with the ImageView from the layout.
        imageView = findViewById(R.id.imageView)
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