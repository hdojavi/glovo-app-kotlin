package com.jhernando.glovomvvm.model.business

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepositoryImpl :
    ProductRepository {

    private var products = MutableLiveData<List<Product>>()

    override fun getProducts(): MutableLiveData<List<Product>> {
        return products;
    }

    override fun callProductsAPI(id: Int) {
        var productsList: ArrayList<Product>? = ArrayList<Product>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getProducts(id)

        call.enqueue(object : Callback<ArrayList<Product>> {
            override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<Product>>,
                response: Response<ArrayList<Product>>
            ) {
                response.body()?.forEach { product: Product -> productsList?.add(product) };
                products.value = productsList
            }
        })
    }
}

