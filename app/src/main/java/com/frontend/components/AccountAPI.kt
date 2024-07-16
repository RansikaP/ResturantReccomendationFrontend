package com.frontend.components

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

/**
 * Fetches user information from the backend server.
 *
 * @param context The context used to create the request queue.
 * @param userId The ID of the user whose information is being fetched.
 * @param accessToken The access token for authentication.
 * @param callback The callback to handle the fetched user information.
 */
fun getUserInfo(context: Context, userId: String, accessToken: String, callback: (User?) -> Unit) {
    val queue = Volley.newRequestQueue(context)
    val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/user?id=$userId"

    // Create a GET request to fetch user information
    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.GET, url, null,
        { response ->
            // Parse the response and create a User object
            val user = User(
                response.getString("id"),
                response.getString("firstName"),
                response.getString("lastName"),
                response.getString("email")
            )
            // Pass the User object to the callback
            callback(user)
        },
        { error ->
            // Log an error message and pass null to the callback
            Log.e("AccountAPI", "Error fetching user info", error)
            callback(null)
        }) {
        override fun getHeaders(): MutableMap<String, String> {
            // Add the authorization header with the access token
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $accessToken"
            return headers
        }
    }

    // Add the request to the request queue
    queue.add(jsonObjectRequest)
}

/**
 * Updates user information on the backend server.
 *
 * @param context The context used to create the request queue.
 * @param userId The ID of the user whose information is being updated.
 * @param updatedUser The updated user information.
 * @param accessToken The access token for authentication.
 * @param callback The callback to handle the success or failure of the update.
 */
fun updateUserInfo(context: Context, userId: String, updatedUser: User, accessToken: String, callback: (Boolean) -> Unit) {
    val queue = Volley.newRequestQueue(context)
    val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/user?id=$userId"

    // Create a JSON object with the updated user information
    val jsonObject = JSONObject().apply {
        put("firstName", updatedUser.firstName)
        put("lastName", updatedUser.lastName)
        put("email", updatedUser.email)
    }

    // Create a PUT request to update user information
    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.PUT, url, jsonObject,
        { response ->
            // Call the callback with true indicating success
            callback(true)
        },
        { error ->
            // Log an error message and call the callback with false indicating failure
            Log.e("AccountAPI", "Error updating user info", error)
            callback(false)
        }) {
        override fun getHeaders(): MutableMap<String, String> {
            // Add the authorization header with the access token
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $accessToken"
            return headers
        }
    }

    // Add the request to the request queue
    queue.add(jsonObjectRequest)
}

/**
 * Changes user password on the backend server.
 *
 * @param context The context used to create the request queue.
 * @param userId The ID of the user whose password is being changed.
 * @param newPassword The new password.
 * @param accessToken The access token for authentication.
 * @param callback The callback to handle the success or failure of the password change.
 */
fun changeUserPassword(context: Context, userId: String, newPassword: String, accessToken: String, callback: (Boolean) -> Unit) {
    val queue = Volley.newRequestQueue(context)
    val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/change-pass"

    // Create a JSON object with the password change request
    val jsonObject = JSONObject().apply {
        put("userId", userId)
        put("newPassword", newPassword)
    }

    // Create a PATCH request to change the user password
    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.PATCH, url, jsonObject,
        { response ->
            // Call the callback with true indicating success
            callback(true)
        },
        { error ->
            // Log an error message and call the callback with false indicating failure
            Log.e("AccountAPI", "Error changing user password", error)
            callback(false)
        }) {
        override fun getHeaders(): MutableMap<String, String> {
            // Add the authorization header with the access token
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $accessToken"
            return headers
        }
    }

    // Add the request to the request queue
    queue.add(jsonObjectRequest)
}

/**
 * Deletes the user account on the backend server.
 *
 * @param context The context used to create the request queue.
 * @param userId The ID of the user whose account is being deleted.
 * @param accessToken The access token for authentication.
 * @param callback The callback to handle the success or failure of the deletion.
 */
fun deleteUserAccount(context: Context, userId: String, accessToken: String, callback: (Boolean) -> Unit) {
    val queue = Volley.newRequestQueue(context)
    val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/user?id=$userId"

    // Create a DELETE request to delete the user account
    val jsonObjectRequest = object : JsonObjectRequest(
        Request.Method.DELETE, url, null,
        { response ->
            // Call the callback with true indicating success
            callback(true)
        },
        { error ->
            // Log an error message and call the callback with false indicating failure
            Log.e("AccountAPI", "Error deleting user account", error)
            callback(false)
        }) {
        override fun getHeaders(): MutableMap<String, String> {
            // Add the authorization header with the access token
            val headers = HashMap<String, String>()
            headers["Authorization"] = "Bearer $accessToken"
            return headers
        }
    }

    // Add the request to the request queue
    queue.add(jsonObjectRequest)
}

/**
 * Data class representing a user.
 *
 * @param id The ID of the user.
 * @param firstName The first name of the user.
 * @param lastName The last name of the user.
 * @param email The email address of the user.
 */
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
