package com.jhernando.glovomvvm.model

import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.Order
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.model.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface ApiService {
    @GET("businesses/category/{id}")
    fun getBusiness(@Path("id") id: Int): Call<ArrayList<Business>>

    @GET("products/business/{id}")
    fun getProducts(@Path("id") id: Int): Call<ArrayList<Product>>

    @GET("orders/user/{id}")
    fun getOrders(@Path("id") id: Int): Call<ArrayList<Order>>

    @GET("login")
    fun getUser(@Body user: User): Call<User>
}