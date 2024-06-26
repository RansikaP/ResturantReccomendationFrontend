package com.frontend

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.android.volley.Network
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.frontend.components.Register
import com.frontend.components.bottom_nav
import org.json.JSONObject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.signup_button)

        loginButton.setOnClickListener {
            // Start another activity
            authenticateAPI()
        }

       registerButton.setOnClickListener {
            // Start another activity
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    private fun startLogin() {
        setContentView(R.layout.login_page)
        val loginButton = findViewById<Button>(R.id.login_button)
        loginButton.setOnClickListener {
            authenticateAPI()
        }
    }

    private fun authenticateAPI() {

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

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, jsonObject,
            { response ->
                val access_token = response.getString("access_token")
                val refresh_token = response.getString("refresh_token")
                if (!access_token.isNullOrEmpty()) {
                    Log.e("VolleyResponse", access_token)

                    // Save access token to SharedPreferences
                    val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("access_token", access_token)
                    editor.putString("refresh_token", refresh_token)
                    editor.putString("userId", email)
                    editor.apply()


                    // Start another activity after authentication

                    val intent = Intent(this, bottom_nav::class.java)
                    startActivity(intent)
                }
                else {
                    handleFailedLogin()
                }
            },
            { error ->
                Log.e("VolleyError", error.toString())
                handleFailedLogin()
            })

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest)

    }

    private fun handleFailedLogin() {
        setContentView(R.layout.failed_login)
        val failedLoginButton = findViewById<Button>(R.id.failed_login_button)
        failedLoginButton.setOnClickListener {
            startLogin() // Restart the login process
        }
    }
}
