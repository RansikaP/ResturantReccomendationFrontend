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
<<<<<<< Updated upstream
=======

    fun authenticateAPI() {

        val usernameInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)
        val email = usernameInput.text.toString()
        val password = passwordInput.text.toString()

        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network: Network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }

        val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/api/v1/auth/authenticate"

        val jsonObject = JSONObject().apply {
            put("email", email)
            put("password", password)
        }

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
            Response.Listener { response ->
                val access_token = response.getString("access_token")
                val refresh_token = response.getString("refresh_token")
                Log.e("VolleyResponse", access_token)

                // Save access token to SharedPreferences
                val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("access_token", access_token)
                editor.putString("refresh_token", refresh_token)
                editor.apply()


                // Start another activity after authentication
                val intent = Intent(this, bottom_nav::class.java)
                startActivity(intent)
            },
            Response.ErrorListener { error ->
                Log.e("VolleyError", error.toString())
            })

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest)
    }



>>>>>>> Stashed changes
}
