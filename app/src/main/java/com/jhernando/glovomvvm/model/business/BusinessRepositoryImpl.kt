package com.jhernando.glovomvvm.model.business

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.ApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BusinessRepositoryImpl :
    BusinessRepository {

    private var businesses = MutableLiveData<List<Business>>()

    override fun getBusiness(): MutableLiveData<List<Business>> {
        return businesses;
    }

    override fun callBusinessAPI(id: Int) {
        var businessList: ArrayList<Business>? = ArrayList<Business>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getBusiness(id)

        call.enqueue(object : Callback<ArrayList<Business>> {
            override fun onFailure(call: Call<ArrayList<Business>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<ArrayList<Business>>,
                response: Response<ArrayList<Business>>
            ) {
                response.body()?.forEach { business: Business -> businessList?.add(business) };
                businesses.value = businessList
            }
        })
    }
}

