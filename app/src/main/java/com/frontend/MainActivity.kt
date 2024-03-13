package com.frontend

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import com.frontend.components.Register

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val registerButton = findViewById<Button>(R.id.register)

        registerButton.setOnClickListener {
            // Start another activity
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }
}
