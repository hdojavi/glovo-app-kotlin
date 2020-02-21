package com.jhernando.glovomvvm.model.business

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderProductRepositoryImpl :
    OrderProductRepository {

    private var products = MutableLiveData<List<Product>>()

    override fun getOrderProducts(): MutableLiveData<List<Product>> {
        return products;
    }

    override fun callOrderProductsAPI(cart: ArrayList<Product>) {
       products.value = cart
    }
}

