package com.jhernando.glovomvvm.model.business

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class OrderProductObservable: BaseObservable(){

    private var orderProductRepository: OrderProductRepository =
        OrderProductRepositoryImpl()

    fun callOrderProducts(cart: ArrayList<Product>){
        orderProductRepository.callOrderProductsAPI(cart)
    }

    fun getOrderProducts() : MutableLiveData<List<Product>>{
        return orderProductRepository.getOrderProducts()
    }
}