package com.jhernando.glovomvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.model.business.UserObservable
import com.jhernando.glovomvvm.model.user.User
import com.jhernando.glovomvvm.view.MainActivity
import com.jhernando.glovomvvm.view.user.UserLoginActivity
import java.math.BigInteger
import java.security.MessageDigest


class UserViewModel : ViewModel() {
    private var userObservable: UserObservable = UserObservable()

    fun callUser(user: User) {
        userObservable.callUser(user)
    }

    fun getUser(): MutableLiveData<User> {
        return userObservable.getUser()
    }

    fun goLogin(view: View, email: TextView, password: TextView) {
        var context: Context = view.context

        val userJson =
            JsonParser().parse("{\"email\": " + email.text.toString() + ", \"password\": " + password.text.toString().md5() + "}")
                .asJsonObject
        callUser(User(userJson))
        var userResponse = User(null)
        getUser()?.observeForever(Observer { user: User -> userResponse = user })

        if (userResponse?.id != 0 && userResponse?.id != null) {
            val userDetails = context.getSharedPreferences("userdetails", Context.MODE_PRIVATE)
            val edit = userDetails.edit()
            edit.putInt("userId", userResponse.id!!)
            edit.putString("username", userResponse.nickname)
            edit.putString("email", userResponse.email)
            edit.putString("pass", userResponse.password)
            edit.putString("tlfn", userResponse.tlfn)
            edit.apply()
            Toast.makeText(context, "Usuario correcto", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, MainActivity::class.java)
            context!!.startActivity(intent)
        } else {
            Toast.makeText(context, "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }

    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }
}