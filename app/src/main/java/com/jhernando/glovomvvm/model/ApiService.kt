package com.jhernando.glovomvvm.model

import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface ApiService {
    @GET("businesses/category/{id}")
    fun getBusiness(@Path("id") id: Int): Call<ArrayList<Business>>

    @GET("products/business/{id}")
    fun getProducts(@Path("id") id: Int): Call<ArrayList<Product>>
}