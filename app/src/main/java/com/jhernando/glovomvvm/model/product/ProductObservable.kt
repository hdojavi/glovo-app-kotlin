package com.jhernando.glovomvvm.model.business

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class ProductObservable: BaseObservable(){

    private var productRepository: ProductRepository =
        ProductRepositoryImpl()

    fun callProducts(id: Int){
        productRepository.callProductsAPI(id)
    }

    fun getProducts() : MutableLiveData<List<Product>>{
        return productRepository.getProducts()
    }
}