package com.frontend

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.frontend.components.Register
import com.frontend.components.bottom_nav
import com.frontend.components.swipe_home_page

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.signup_button)

        loginButton.setOnClickListener {
            // Start another activity
            val intent = Intent(this, bottom_nav::class.java)
            startActivity(intent)
        }

       registerButton.setOnClickListener {
            // Start another activity
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }



    }
}
