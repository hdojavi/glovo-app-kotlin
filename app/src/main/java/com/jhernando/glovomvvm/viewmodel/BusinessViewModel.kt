package com.jhernando.glovomvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.BusinessObservable
import com.jhernando.glovomvvm.view.business.RecyclerBusinessAdapter
import com.jhernando.glovomvvm.view.product.ProductActivity


class BusinessViewModel : ViewModel() {

    private var businessObservable: BusinessObservable =
        BusinessObservable()
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

    fun goToBusiness(view: View, business: Business){
        var context: Context? = view.context
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("business", business)
        context!!.startActivity(intent)
    }
}