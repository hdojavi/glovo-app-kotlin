package com.jhernando.glovomvvm.model.business

import androidx.lifecycle.MutableLiveData

interface OrderRepository {
    fun getOrders(): MutableLiveData<List<Order>>
    fun callOrdersAPI(id: Int)
}