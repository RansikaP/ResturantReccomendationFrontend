package com.frontend.components

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

fun getUserInfo(context: Context, userId: String, accessToken: String, callback: (User?) -> Unit) {
    val queue = Volley.newRequestQueue(context)
    val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/user?id=$userId"

    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.GET, url, null,
        { response ->
            val user = User(
                response.getString("id"),
                response.getString("firstName"),
                response.getString("lastName"),
                response.getString("email")
            )
            callback(user)
        },
        { error ->
            Log.e("AccountAPI", "Error fetching user info", error)
            callback(null)
        }) {
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $accessToken"
            return headers
        }
    }

    queue.add(jsonObjectRequest)
}

fun updateUserInfo(context: Context, userId: String, updatedUser: User, accessToken: String, callback: (Boolean) -> Unit) {
    val queue = Volley.newRequestQueue(context)
    val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/user?id=$userId"

    val jsonObject = JSONObject().apply {
        put("firstName", updatedUser.firstName)
        put("lastName", updatedUser.lastName)
        put("email", updatedUser.email)
    }

    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.PUT, url, jsonObject,
        { response ->
            callback(true)
        },
        { error ->
            Log.e("AccountAPI", "Error updating user info", error)
            callback(false)
        }) {
        override fun getHeaders(): MutableMap<String, String> {
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $accessToken"
            return headers
        }
    }

    queue.add(jsonObjectRequest)
}

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)