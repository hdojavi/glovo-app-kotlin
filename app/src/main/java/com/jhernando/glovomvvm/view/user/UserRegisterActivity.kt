package com.jhernando.glovomvvm.view.user

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.model.user.User
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class UserRegisterActivity : AppCompatActivity() {
    var btnRegister: Button? = null
    var btnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonRegisterR.setOnClickListener(View.OnClickListener { goRegister() })
        buttonLoginR.setOnClickListener(View.OnClickListener { goLogin() })
    }

    private fun goLogin() {
        val intent = Intent(this, UserLoginActivity::class.java)
        startActivity(intent)
    }

    private fun goRegister() {
        val email = findViewById<EditText>(R.id.emailRegister)
        val nick = findViewById<EditText>(R.id.nickRegister)
        val password = findViewById<EditText>(R.id.passRegister)
        val confPassword = findViewById<EditText>(R.id.confirmPassRegister)
        if (TextUtils.isEmpty(email.text) || TextUtils.isEmpty(nick.text) || TextUtils.isEmpty(
                password.text
            ) || TextUtils.isEmpty(confPassword.text)
        ) {
            Toast.makeText(this, "Todos los campos requeridos", Toast.LENGTH_SHORT).show()
        } else {
            if (password.text.toString() == confPassword.text.toString()) {
                var userObject = JsonObject()
                userObject.add("email", emailLogin.text.toString() as JsonElement)
                userObject.add("password",passLogin.text.toString() as JsonElement)
                userObject.add("nickname",nickRegister.text.toString() as JsonElement)
                val user = User(userObject)
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }

}