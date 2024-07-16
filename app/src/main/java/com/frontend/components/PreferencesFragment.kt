package com.frontend.components

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.frontend.R



class PreferencesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.preferences, container, false)
        val context = context
        if (context != null) {
            // Now you can use the context to access getSharedPreferences
            val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            val user = sharedPreferences.getString("userId", "")
            val accessToken = sharedPreferences.getString("access_token", "")
            Log.d("logged user access token", accessToken.toString())
            preferencesAPI(context, user.toString(), accessToken.toString()) {
                // Callback code to update UI after preferencesAPI completes
                val halal = sharedPreferences.getString("halal", "")
                val vege = sharedPreferences.getString("vegetarian", "")
                val vegan = sharedPreferences.getString("vegan", "")
                val gluten = sharedPreferences.getString("glutenFree", "")
                val distance = sharedPreferences.getString("distance", "")

                val halalSwitch: Switch = view.findViewById(R.id.pref_halal_switch)
                val VegeSwitch: Switch = view.findViewById(R.id.pref_vege_switch)
                val VeganSwitch: Switch = view.findViewById(R.id.pref_vegan_switch)
                val GlutenSwitch: Switch = view.findViewById(R.id.pref_gluten_switch)
                val distanceSeekBar: SeekBar = view.findViewById(R.id.distance_seekbar)
                val distanceTextView: TextView = view.findViewById(R.id.pref_distance_textview)

                // Set the switch state based on the preference values
                halalSwitch.isChecked = halal.equals("true", ignoreCase = true)
                VegeSwitch.isChecked = vege.equals("true", ignoreCase = true)
                VeganSwitch.isChecked = vegan.equals("true", ignoreCase = true)
                GlutenSwitch.isChecked = gluten.equals("true", ignoreCase = true)

                // Set the SeekBar progress based on the distance value
                distanceSeekBar.progress = distance?.toIntOrNull() ?: 0
                distanceTextView.text = "Distance: ${distanceSeekBar.progress} units"

                // Update the distance preference when SeekBar value changes
                distanceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        distanceTextView.text = "Distance: $progress units"
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        // Optional: handle the start of SeekBar touch
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        // Save the new distance value to SharedPreferences
                        sharedPreferences.edit().putString("distance", seekBar?.progress.toString()).apply()
                    }
                })
            }
        }

        return view
    }
}