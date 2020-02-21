package com.jhernando.glovomvvm.model.business

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class OrderDetailObservable: BaseObservable(){

    private var orderDetailRepository: OrderDetailRepository =
        OrderDetailRepositoryImpl()

    fun callOrderDetails(id: Int){
        orderDetailRepository.callOrderDetailsAPI(id)
    }

    fun getOrderDetails() : MutableLiveData<List<OrderDetail>>{
        return orderDetailRepository.getOrderDetails()
    }
}