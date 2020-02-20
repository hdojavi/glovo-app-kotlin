package com.jhernando.glovomvvm.view.user

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityLoginBinding
import com.jhernando.glovomvvm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_login.*

class UserLoginActivity : AppCompatActivity() {
    private var userViewModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonRegisterL.setOnClickListener(View.OnClickListener { goRegister() })

        setupBindings(savedInstanceState)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        activityMainBinding.setModel(userViewModel)
    }

    private fun goRegister() {
        val intent = Intent(this, UserRegisterActivity::class.java)
        startActivity(intent)
    }
}