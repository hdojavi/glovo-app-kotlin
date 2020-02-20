package com.jhernando.glovomvvm.model.user

import com.google.gson.JsonObject
import java.io.Serializable

class User(productJson: JsonObject?) : Serializable {
    var id: Int? = 0
    var email: String? = null
    var nickname: String? = null
    var password: String? = null
    var tlfn: String? = null

    init {
        try {
            id = productJson?.get("id")?.asInt
            email = productJson?.get("email")?.asString
            nickname = productJson?.get("nickname")?.asString
            password = productJson?.get("password")?.asString
            tlfn = productJson?.get("tlfn")?.asString
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}