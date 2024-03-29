package com.frontend.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.frontend.R

class FavouritesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var textViewHeading: TextView
    private val items = arrayOf(
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 1", "Item 2", "Item 3", "Item 4", "Item 5",
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.favourite_page, container, false)

        textViewHeading = view.findViewById(R.id.textViewHeading)
        recyclerView = view.findViewById(R.id.recyclerView)

        // Set text for the heading
        textViewHeading.text = "Favourites"

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = ItemAdapter(items)

        return view
    }

    // RecyclerView adapter
    inner class ItemAdapter(private val items: Array<String>) :
        RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.textViewTitle)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.textView.text = item
            holder.itemView.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked on $item", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount(): Int = items.size
    }
}