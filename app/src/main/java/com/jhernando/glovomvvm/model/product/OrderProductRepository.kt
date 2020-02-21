package com.jhernando.glovomvvm.model.business

import androidx.lifecycle.MutableLiveData

interface OrderProductRepository {
    fun getOrderProducts(): MutableLiveData<List<Product>>
    fun callOrderProductsAPI(cart: ArrayList<Product>)
}