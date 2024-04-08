package com.frontend

import android.os.Bundle
<<<<<<< Updated upstream
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
=======
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.android.volley.Network
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.frontend.components.Register
import com.frontend.components.bottom_nav
import com.frontend.components.swipe_home_page
import org.json.JSONObject
>>>>>>> Stashed changes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("here","here")
        super.onCreate(savedInstanceState)
<<<<<<< Updated upstream
        setContent {
            ResturantReccomendationFrontendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Michael")
                }
            }
=======
        setContentView(R.layout.login_page)

        val loginButton = findViewById<Button>(R.id.login_button)
        val registerButton = findViewById<Button>(R.id.signup_button)

        loginButton.setOnClickListener {
            // Start another activity
            authenticateAPI()
>>>>>>> Stashed changes
        }
    }

    fun authenticateAPI() {
        Log.e("here1","here")
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
                Log.e("VolleyResponse", access_token)

                // Save access token to SharedPreferences
                val sharedPreferences = getSharedPreferences("auth", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("access_token", access_token)
                editor.putString("refresh_token", refresh_token)
                editor.putString("user_id",email)
                editor.apply()


                // Start another activity after authentication
                if (!access_token.isNullOrEmpty()){
                    val intent = Intent(this, bottom_nav::class.java)
                    startActivity(intent)
                }

            },
            { error ->
                Log.e("VolleyError", error.toString())
            })

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest)
    }



}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ResturantReccomendationFrontendTheme {
        Greeting("Android")
    }
}