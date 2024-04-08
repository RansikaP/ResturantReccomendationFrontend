package com.frontend.components

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.frontend.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class bottom_nav  : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("auth", ComponentActivity.MODE_PRIVATE)
        val username: String = sharedPreferences.getString("user_id", "").toString()
        Log.d("user",username)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_nav)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, PreferencesFragment())
            .commit()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_settings -> replaceFragment(PreferencesFragment())
                R.id.navigation_account -> replaceFragment(RestaurantDetailsFragment())
                R.id.navigation_favourites -> replaceFragment(FavouritesFragment())
                R.id.navigation_restaurant -> replaceFragment(swipeFragment())
            }
            true
        }

        // Set default fragment
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    fun navigateToLikeDetails() {
        // Replace the fragment with the one showing more information about the liked item
        // You can replace the fragment here as per your navigation logic
        // Example:
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RestaurantDetailsFragment())
            .commit()
    }
}