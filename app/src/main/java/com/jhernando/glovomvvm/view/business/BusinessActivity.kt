package com.jhernando.glovomvvm.view.business

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityBusinessBinding
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.viewmodel.BusinessViewModel
import kotlinx.android.synthetic.main.activity_business.*

class BusinessActivity : AppCompatActivity() {
    private var id = 0;

    private var businessViewModel: BusinessViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)

        val intent = intent
        id = intent.getIntExtra("id", 0)
        setupBindings(savedInstanceState)

        arrowBackBusiness.setOnClickListener({ finish() })

        if (id == 1) {
            searchQuery.setHint(R.string.hint_searchSupermarket)
        } else if (id == 2) {
            searchQuery.setHint(R.string.hint_searchRestaurant)
        } else if (id == 3) {
            searchQuery.setHint(R.string.hint_searchBreakfast)
        } else {
            searchQuery.setHint(R.string.hint_search)
        }
    }


    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityBusinessBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_business)

        businessViewModel = ViewModelProvider(this).get(BusinessViewModel::class.java)

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

