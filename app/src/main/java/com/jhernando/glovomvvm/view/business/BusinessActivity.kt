package com.jhernando.glovomvvm.view.business

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityBusinessBinding
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.BusinessViewModel

class BusinessActivity : AppCompatActivity() {
    private var id = 0;
    private var btnBack: ImageView? = null
    private var searchBtn: ImageView? = null
    private var searchQuery: TextView? = null

    private var businessViewModel: BusinessViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)

        id = intent.getIntExtra("id", 0)
        setupBindings(savedInstanceState)

        btnBack = findViewById(R.id.arrowBack)
        searchBtn = findViewById(R.id.buttonSearch)
        searchQuery = findViewById(R.id.searchQuery)
        val intent = intent

        btnBack!!.setOnClickListener(View.OnClickListener { finish() })

        if (id == 1) {
            searchQuery!!.setHint(R.string.hint_searchSupermarket)
        } else if (id == 2) {
            searchQuery!!.setHint(R.string.hint_searchRestaurant)
        } else if (id == 3) {
            searchQuery!!.setHint(R.string.hint_searchBreakfast)
        } else {
            searchQuery!!.setHint(R.string.hint_search)
        }
    }


    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityBusinessBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_business)

        businessViewModel = ViewModelProviders.of(this).get(BusinessViewModel::class.java)

        activityMainBinding.setModel(businessViewModel)
        setupListUpdate()

    }

    fun setupListUpdate() {
        businessViewModel?.callBusinesses(id)

        businessViewModel?.getBusinesses()?.observe(this, Observer { businesses: List<Business> ->
            businessViewModel?.setBusinessInRecyclerAdapter(businesses)
        })
    }

}

