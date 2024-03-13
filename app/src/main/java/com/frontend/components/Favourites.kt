package com.frontend

import android.os.Bundle
import android.widget.AdapterView
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

import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.Button
import android.widget.Toast
import androidx.compose.material3.FloatingActionButton

class Favourites : ComponentActivity() {

    private lateinit var listView: ListView
    private lateinit var textViewHeading: TextView
    private lateinit var fabBack: FloatingActionButton
    private val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5","Item 1", "Item 2", "Item 3", "Item 4", "Item 5","Item 1", "Item 2", "Item 3", "Item 4", "Item 5","Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favourite_page)

        listView = findViewById(R.id.listView)
        textViewHeading = findViewById(R.id.textViewHeading)

        // Set text for the heading
        textViewHeading.text = "Favourites"

        listView = findViewById(R.id.listView)

        val adapter = ArrayAdapter<String>(this, R.layout.list_item, R.id.textViewTitle, items)

        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this@Favourites, selectedItem, Toast.LENGTH_SHORT).show()
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val item = items[position]
            Toast.makeText(this@Favourites, "Clicked on $item", Toast.LENGTH_SHORT).show()
        }

        adapter.notifyDataSetChanged()
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