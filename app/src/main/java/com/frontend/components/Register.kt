package com.frontend.components

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.frontend.MainActivity
import com.frontend.R

class Register: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_page)

        val loginButton =findViewById<Button>(R.id.Login)
        val registerButton = findViewById<Button>(R.id.buttonRegister)
        val fname = findViewById<EditText>(R.id.editTextfname)
        val lname = findViewById<EditText>(R.id.editTextlname)
        val email = findViewById<EditText>(R.id.editTextemail)
        val password = findViewById<EditText>(R.id.editTextpassword)
        val city = findViewById<EditText>(R.id.editTextCity)
        val street = findViewById<EditText>(R.id.editTextStreet)
        val postal = findViewById<EditText>(R.id.editTextPostal)
        val error = findViewById<TextView>(R.id.error)
        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        registerButton.setOnClickListener {
            // Start another activity

            val fname2 = fname.text.toString()
            val lname2 = lname.text.toString()
            val email2 = email.text.toString()
            val password2 = password.text.toString()
            val city2 = city.text.toString()
            val street2 = street.text.toString()
            val postal2 = postal.text.toString()

            if (fname2.isEmpty() || lname2.isEmpty() || email2.isEmpty() || password2.isEmpty() || city2.isEmpty() || street2.isEmpty() || postal2.isEmpty()) {
                // At least one field is empty
                error.setText("Fill in the fields")
            } else {
                // All fields are filled
                error.setText("")
            }
            val text = fname2 + "," +lname2 + "," +email2 + "," +password2 + "," +city2 + "," +street2 + "," +postal2
            Log.d("Register", "Text entered: $text")
        }
    }
}