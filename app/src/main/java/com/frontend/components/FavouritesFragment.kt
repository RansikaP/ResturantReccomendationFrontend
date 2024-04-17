package com.frontend.components

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Network
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import com.frontend.R

class FavouritesFragment : Fragment() {

    data class Item(val favouriteId: String, val userId: String, val restaurantId: String)

    private lateinit var recyclerView: RecyclerView
    private lateinit var textViewHeading: TextView
    private val items = ArrayList<Item>()
    private lateinit var itemAdapter: ItemAdapter

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
        itemAdapter = ItemAdapter(items)
        recyclerView.adapter = itemAdapter

        // This is the userID to filter the items
        val userIdToFilter = "Patrick2"

        favouriteUser(requireContext(), userIdToFilter)

        return view
    }

    // RecyclerView adapter
    inner class ItemAdapter(private val items: List<Item>) :
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
            holder.textView.text = "${item.restaurantId}"
            holder.itemView.setOnClickListener {
                Toast.makeText(requireContext(), "Clicked on ${item.favouriteId}", Toast.LENGTH_SHORT).show()
            }
        }

        override fun getItemCount(): Int = items.size
    }

    fun favouriteUser(context: Context, userIdToFilter: String) {
        // Instantiate the cache
        val cache = DiskBasedCache(context.cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network: Network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = Volley.newRequestQueue(context)

        val url = "https://restaurant-tinder-backend-1c3d5e1e49c2.herokuapp.com/favourite/$userIdToFilter"

        val stringRequest = object : StringRequest(
            Request.Method.GET, // Use GET method
            url, // URL for the request
            Response.Listener { response ->
                try {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)

                        // Extract values from the JSONObject
                        val favouriteId = jsonObject.getString("favouriteId")
                        val userId = jsonObject.getString("userId")
                        val restaurantId = jsonObject.getString("restaurantId")

                        // Check if the item's userID matches the provided userID
                        if (userId == userIdToFilter) {
                            // Create Item object and add to the items list
                            val item = Item(favouriteId, userId, restaurantId)
                            items.add(item)
                        }
                    }

                    // Notify the adapter that data set has changed
                    itemAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Log.e("VolleyError", error.toString())
            }) {

            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                // Add the Bearer token to the Authorization header
                val accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDIiwiaWF0IjoxNzEyMjcwMTM4LCJleHAiOjE3MTIzNTY1Mzh9.hQzJdgm42mg5l2xd_M4_Kn1KpDTOBVWJMAj710Sc4eI"
                headers["Authorization"] = "Bearer $accessToken"
                return headers
            }
        }

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest)
    }
}
