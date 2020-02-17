package com.jhernando.glovomvvm.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.view.businessActivity

class HomeFragment : Fragment() {
    var searchBtn: ImageView? = null
    var searchQuery: TextView? = null
    var supermarketBtn: ImageView? = null
    var breakfastBtn: ImageView? = null
    var restaurantBtn: ImageView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_home_fragment, null)
        searchQuery = view.findViewById(R.id.searchQuery)
        searchBtn = view.findViewById(R.id.buttonSearch)
        supermarketBtn = view.findViewById(R.id.buttonSupermarket)
        restaurantBtn = view.findViewById(R.id.buttonRestaurant)
        breakfastBtn = view.findViewById(R.id.buttonBreakfast)
        searchBtn!!.setOnClickListener(View.OnClickListener { goSearch() })
        supermarketBtn!!.setOnClickListener(View.OnClickListener { goCategory(1) })
        restaurantBtn!!.setOnClickListener(View.OnClickListener { goCategory(2) })
        breakfastBtn!!.setOnClickListener(View.OnClickListener { goCategory(3) })
        return view
    }

    fun goSearch() {
        val query = searchQuery!!.text.toString()
        if (query.isEmpty()) {
            Toast.makeText(context, "Nada que buscar...", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(context, businessActivity::class.java)
            intent.putExtra("id", 0)
            intent.putExtra("query", query)
            startActivity(intent)
        }
    }

    fun goCategory(id: Int) {
        val intent = Intent(context, businessActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}
