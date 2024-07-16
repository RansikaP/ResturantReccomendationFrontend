package com.frontend.components

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.frontend.R

class AccountFragment : Fragment() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var updateButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.account_page, container, false)

        firstNameEditText = view.findViewById(R.id.first_name_input)
        lastNameEditText = view.findViewById(R.id.last_name_input)
        emailEditText = view.findViewById(R.id.email_input)
        updateButton = view.findViewById(R.id.update_account_button)

        val sharedPreferences = requireContext().getSharedPreferences("auth", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", "") ?: ""
        val accessToken = sharedPreferences.getString("access_token", "") ?: ""

        getUserInfo(requireContext(), userId, accessToken) { user ->
            user?.let {
                firstNameEditText.setText(it.firstName)
                lastNameEditText.setText(it.lastName)
                emailEditText.setText(it.email)
            }
        }

        updateButton.setOnClickListener {
            val updatedUser = User(
                userId,
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                emailEditText.text.toString()
            )
            updateUserInfo(requireContext(), userId, updatedUser, accessToken) { success ->
                if (success) {
                    Toast.makeText(context, "Account updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to update account", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }
}