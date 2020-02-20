package com.jhernando.glovomvvm.model.business

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.jhernando.glovomvvm.model.ordermethod.OrderMethod
import com.jhernando.glovomvvm.model.user.User
import org.json.JSONObject
import java.io.Serializable

class Order(productJson: JsonObject?) : Serializable {
    var id = 0
    var orderdate: String? = null
    var pricetotal:Float = 0F
    var user: User? = null
    var orderMethod: OrderMethod? = null
    var business: Business? = null

    init {
        try {
            id = productJson!!.get("id").asInt
            orderdate = productJson!!.get("orderdate").asString
            pricetotal = productJson!!.get("pricetotal").asFloat
            user = productJson!!.get("user") as User
            orderMethod = productJson!!.get("ordermethod") as OrderMethod
            business = productJson!!.get("business") as Business
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}