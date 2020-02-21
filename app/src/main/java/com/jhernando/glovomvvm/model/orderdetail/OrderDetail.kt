package com.jhernando.glovomvvm.model.business

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jhernando.glovomvvm.model.ordermethod.OrderMethod
import com.jhernando.glovomvvm.model.user.User
import org.json.JSONObject
import java.io.Serializable

class OrderDetail(orderDetailJson: JsonObject?) : Serializable {
    var id = 0
    var productid: String? = null
    var productname: String? = null
    var quantity: Int? = 0
    var price: Float? = 0F
    var order: Order? = null

    init {
        try {
            id = orderDetailJson!!.get("id").asInt
            productid = orderDetailJson!!.get("productid").asString
            productname = orderDetailJson!!.get("productname").asString
            quantity = orderDetailJson!!.get("quantity").asInt
            price = orderDetailJson!!.get("ordermethod").asFloat
            order = orderDetailJson!!.get("orderBusi") as Order
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}