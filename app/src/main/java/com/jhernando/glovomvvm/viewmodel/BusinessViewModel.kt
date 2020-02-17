package com.jhernando.glovomvvm.viewmodel

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.model.Business
import com.jhernando.glovomvvm.model.BusinessObservable
import com.jhernando.glovomvvm.view.RecyclerBusinessAdapter
import com.squareup.picasso.Picasso

class BusinessViewModel : ViewModel() {

    private var businessObservable: BusinessObservable = BusinessObservable()
    private var recyclerBusinessAdapter: RecyclerBusinessAdapter? = null

    fun callBusinesses(id: Int) {
        businessObservable.callBusinesses(id)
    }

    fun getBusinesses(): MutableLiveData<List<Business>> {
        return businessObservable.getBusinesses()
    }

    fun setBusinessInRecyclerAdapter(businesses: List<Business>) {
        recyclerBusinessAdapter?.setBusinessesList(businesses)
        recyclerBusinessAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerBusinessAdapter(): RecyclerBusinessAdapter? {
        recyclerBusinessAdapter = RecyclerBusinessAdapter(this, R.layout.card_business)
        return recyclerBusinessAdapter
    }

    fun getBusinessAt(position: Int): Business? {
        var businesses: List<Business>? = businessObservable.getBusinesses().value
        return businesses?.get(position)
    }
}