package com.jhernando.glovomvvm.model.business

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class OrderObservable: BaseObservable(){

    private var orderRepository: OrderRepository =
        OrderRepositoryImpl()

    fun callOrders(id: Int){
        orderRepository.callOrdersAPI(id)
    }

    fun getOrders() : MutableLiveData<List<Order>>{
        return orderRepository.getOrders()
    }
}