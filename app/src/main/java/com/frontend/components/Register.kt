package com.frontend.components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.android.volley.Network
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import com.frontend.MainActivity
import com.frontend.R
import org.json.JSONObject

class Register: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_page)

        val loginButton =findViewById<Button>(R.id.Login)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        val overlayView = findViewById<View>(R.id.overlayView)

        val fname = findViewById<EditText>(R.id.editTextfname)
        val lname = findViewById<EditText>(R.id.editTextlname)
        val email = findViewById<EditText>(R.id.editTextemail)
        val password = findViewById<EditText>(R.id.editTextpassword)
        val city = findViewById<EditText>(R.id.editTextCity)
        val street = findViewById<EditText>(R.id.editTextStreet)
        val postal = findViewById<EditText>(R.id.editTextPostal)
        val error = findViewById<TextView>(R.id.error)

        //button to go to login page
        loginButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //dismiss keyboard
        overlayView.setOnClickListener {
            // Clear focus from any currently focused text field
            currentFocus?.clearFocus()

            // Hide the keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        //checks information entered in fields and registers new user
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
                registerNewUser(fname2,lname2,email2,password2,city2,street2,postal2);
            }
            val text = fname2 + "," +lname2 + "," +email2 + "," +password2 + "," +city2 + "," +street2 + "," +postal2
            Log.d("Register", "Text entered: $text")
        }
    }

    fun registerNewUser(fname:String,lname:String,email:String,password:String, city:String,street:String,postal:String){
        // Instantiate the cache
        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network: Network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }

        val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/api/v1/auth/register"

        val jsonObject = JSONObject().apply {
            put("fname", fname)
            put("lname", lname)
            put("email", email)
            put("password", password)
            put("address", JSONObject().apply {
                put("street", street)
                put("city", city)
                put("postalCode", postal)
            })
        }

        Log.e("here","here")
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
                editor.apply()


                // Start another activity after authentication
                val intent = Intent(this, bottom_nav::class.java)
                startActivity(intent)
            },
            { error ->
                Log.e("VolleyError", error.toString())
            })

        // Add the request to the RequestQueue.
        requestQueue.add(jsonObjectRequest)
    }
}