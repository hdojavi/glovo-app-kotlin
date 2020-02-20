package com.jhernando.glovomvvm.model.business

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderRepositoryImpl :
    OrderRepository {

    private var orders = MutableLiveData<List<Order>>()

    override fun getOrders(): MutableLiveData<List<Order>> {
        return orders;
    }

    override fun callOrdersAPI(id: Int) {
        var orderList: ArrayList<Order>? = ArrayList<Order>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getOrders(id)

        call.enqueue(object : Callback<ArrayList<Order>> {
            override fun onFailure(call: Call<ArrayList<Order>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<Order>>,
                response: Response<ArrayList<Order>>
            ) {
                response.body()?.forEach { order: Order -> orderList?.add(order) };
                orders.value = orderList
            }
        })
    }
}

