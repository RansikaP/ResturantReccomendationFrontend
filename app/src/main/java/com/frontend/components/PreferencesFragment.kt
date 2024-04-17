package com.frontend.components

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.frontend.R


class PreferencesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val context = context
        if (context != null) {
            // Now you can use the context to access getSharedPreferences
            val sharedPreferences= context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            val user = sharedPreferences.getString("userId","")
            val accessToken = sharedPreferences.getString("access_token","")
            Log.d("logged user access token", accessToken.toString())
            preferencesAPI(context,user.toString(),accessToken.toString())

            val halal = sharedPreferences.getString("halal", "")
            Log.d("halal", halal.toString())
        }



        return inflater.inflate(R.layout.preferences, container, false)
    }
}

