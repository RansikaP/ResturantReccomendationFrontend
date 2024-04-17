package com.frontend.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Switch
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

fun preferencesAPI(context: Context) {
    // Instantiate the RequestQueue.
    val queue = Volley.newRequestQueue(context)
    val userId = "user1"
    val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/pref/$userId"

    // Request a string response from the provided URL.
    val stringRequest = object : StringRequest(Request.Method.GET, url,
        Response.Listener<String> { response ->
            // Handle the response
            Log.d("Response Pref", response)
            Log.d("Response Pref" , JSONObject(response).getBoolean("halal").toString())

        },
        Response.ErrorListener { error ->
            // Handle errors
            Log.e("Error", "Error occurred", error)
        }) {
        @Throws(AuthFailureError::class)
        override fun getHeaders(): Map<String, String> {
            val headers = HashMap<String, String>()
            val authToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbDMiLCJpYXQiOjE3MTIyNzI1MTgsImV4cCI6MTcxMjM1ODkxOH0.rEc2mf1v_T9V9H2zwwXbPspDw23TQztFp8lqSeSvGnc"
            headers["Authorization"] = "Bearer $authToken"
            return headers
        }
    }

    // Add the request to the RequestQueue.
    queue.add(stringRequest)
}