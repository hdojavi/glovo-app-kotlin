package com.jhernando.glovomvvm.model.ordermethod

import com.google.gson.JsonObject
import com.jhernando.glovomvvm.model.business.Business
import java.io.Serializable

class OrderMethod(productJson: JsonObject?) : Serializable {
    var id = 0
    var method: String? = null

    init {
        try {
            id = productJson!!.get("id").asInt
            method = productJson!!.get("method").asString
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}