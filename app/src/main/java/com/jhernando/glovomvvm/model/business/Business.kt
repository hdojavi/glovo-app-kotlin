package com.jhernando.glovomvvm.model.business

import com.google.gson.JsonObject
import java.io.Serializable

class Business(businessJson: JsonObject?) : Serializable {
    var id = 0
    var name: String? = null
    var description: String? = null
    var shippingprice:Double = 0.0
    var rate = 0
    var kmaway: Int = 0
    var thumb: String? = null

    init {
        try {
            id = businessJson!!.get("id").asInt
            name = businessJson!!.get("name").asString
            description = businessJson!!.get("description").asString
            shippingprice = businessJson!!.get("shippingprice").asDouble
            rate = businessJson!!.get("rate").asInt
            kmaway = businessJson!!.get("kmaway").asInt
            thumb = businessJson!!.get("thumb").asString
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
}