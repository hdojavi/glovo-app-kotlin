package com.jhernando.glovomvvm.model.business

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.Serializable

class Product(productJson: JsonObject?) : Serializable {
    var id: Int? = 0
    var name: String? = null
    var description: String? = null
    var price: Float? = 0F
    var businessid: Int? = 0
    var business: Business? = null
    var quantity: Int? = 0

    init {
        try {
            id = productJson?.get("id")?.asInt
            name = productJson?.get("name")?.asString
            description = productJson?.get("description")?.asString
            price = productJson?.get("price")?.asFloat
            businessid = productJson?.get("businessId")?.asInt
            business = productJson?.get("business") as Business?
            quantity = productJson?.get("quantity")?.asInt
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}