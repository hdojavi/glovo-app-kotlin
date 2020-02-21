package com.jhernando.glovomvvm.model.business

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderDetailRepositoryImpl :
    OrderDetailRepository {

    private var orderDetails = MutableLiveData<List<OrderDetail>>()

    override fun getOrderDetails(): MutableLiveData<List<OrderDetail>> {
        return orderDetails;
    }

    override fun callOrderDetailsAPI(id: Int) {
        var orderDetailList: ArrayList<OrderDetail>? = ArrayList<OrderDetail>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getOrderDetails(id)

        call.enqueue(object : Callback<ArrayList<OrderDetail>> {
            override fun onFailure(call: Call<ArrayList<OrderDetail>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<OrderDetail>>,
                response: Response<ArrayList<OrderDetail>>
            ) {
                response.body()?.forEach { orderDetail: OrderDetail -> orderDetailList?.add(orderDetail) };
                orderDetails.value = orderDetailList
            }
        })
    }
}

