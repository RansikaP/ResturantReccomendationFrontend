package com.frontend.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.frontend.R
import kotlinx.coroutines.NonDisposableHandle.parent

class swipeFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_home_page, container, false)

        // Find the like and dislike buttons
        val likeButton = view.findViewById<Button>(R.id.btnLike)
        val dislikeButton = view.findViewById<Button>(R.id.btnDislike)

        // Set click listeners for the like and dislike buttons
        likeButton.setOnClickListener { onLikeButtonClick() }
        dislikeButton.setOnClickListener { onDislikeButtonClick() }

        return view
    }

    // Function to handle the click event of the "Like" button.
    private fun onLikeButtonClick() {
        val parentActivity = requireActivity()
        if (parentActivity is bottom_nav) {
            parentActivity.navigateToLikeDetails()
        }
    }
    // Function to handle the click event of the "Dislike" button.
    private fun onDislikeButtonClick() {
        // Display a toast message for disliking
        showToast("Disliked!")
    }

    // Function to display a toast message with the specified text.
    private fun showToast(message: String) {
        // Use requireContext() to get the Context associated with the fragment.
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}