package com.jhernando.glovomvvm.model.business

import androidx.lifecycle.MutableLiveData

interface OrderDetailRepository {
    fun getOrderDetails(): MutableLiveData<List<OrderDetail>>
    fun callOrderDetailsAPI(id: Int)
}