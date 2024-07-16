package com.frontend.components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.frontend.MainActivity
import com.frontend.R

/**
 * Fragment for displaying and updating account information.
 */
class AccountFragment : Fragment() {

    // Declare variables for the UI elements
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteAccountButton: Button
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.account_page, container, false)

        // Initialize the UI elements
        firstNameEditText = view.findViewById(R.id.first_name_input)
        lastNameEditText = view.findViewById(R.id.last_name_input)
        emailEditText = view.findViewById(R.id.email_input)
        passwordEditText = view.findViewById(R.id.new_password_input)
        updateButton = view.findViewById(R.id.update_account_button)
        deleteAccountButton = view.findViewById(R.id.delete_account_button)
        logoutButton = view.findViewById(R.id.logout_button)

        // Get user ID and access token from shared preferences
        val sharedPreferences = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "") ?: ""
        val accessToken = sharedPreferences.getString("access_token", "") ?: ""

        // Fetch user information and populate the UI elements
        getUserInfo(requireContext(), userId, accessToken) { user ->
            user?.let {
                firstNameEditText.setText(it.firstName)
                lastNameEditText.setText(it.lastName)
                emailEditText.setText(it.email)
                // Set a placeholder for the password
                passwordEditText.setText("***")
            }
        }

        // Set a click listener for the update button
        updateButton.setOnClickListener {
            // Create an updated User object
            val updatedUser = User(
                userId,
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                emailEditText.text.toString()
            )
            // Update user information
            updateUserInfo(requireContext(), userId, updatedUser, accessToken) { success ->
                if (success) {
                    // If user information update is successful, proceed to update the password
                    val newPassword = passwordEditText.text.toString()
                    if (newPassword.isNotEmpty() && newPassword != "***") {
                        changeUserPassword(requireContext(), userId, newPassword, accessToken) { passChangeSuccess ->
                            // Display a toast message based on the success or failure of the password change
                            if (passChangeSuccess) {
                                Toast.makeText(context, "Account and password updated successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Account updated but failed to change password", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        // If password is not changed, just display success message for account update
                        Toast.makeText(context, "Account updated successfully", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Failed to update account", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Set a click listener for the delete account button
        deleteAccountButton.setOnClickListener {
            deleteUserAccount(requireContext(), userId, accessToken) { success ->
                if (success) {
                    // Clear shared preferences
                    val editor = sharedPreferences.edit()
                    editor.clear()
                    editor.apply()

                    // Redirect to the login screen
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)

                    // Finish the current activity
                    activity?.finish()
                } else {
                    // Display a toast message indicating failure
                    Toast.makeText(context, "Failed to delete account", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Set a click listener for the logout button
        logoutButton.setOnClickListener {
            // Clear shared preferences
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()

            // Redirect to the login screen
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

            // Finish the current activity
            activity?.finish()
        }

        // Return the view
        return view
    }
}
