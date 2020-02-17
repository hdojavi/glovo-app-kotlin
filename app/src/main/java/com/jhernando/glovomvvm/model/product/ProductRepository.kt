package com.jhernando.glovomvvm.model.business

import androidx.lifecycle.MutableLiveData

interface ProductRepository {
    fun getProducts(): MutableLiveData<List<Product>>
    fun callProductsAPI(id: Int)
}