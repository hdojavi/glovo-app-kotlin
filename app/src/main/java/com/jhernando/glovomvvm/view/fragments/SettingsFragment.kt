package com.jhernando.glovomvvm.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.view.user.UserLoginActivity
import com.jhernando.glovomvvm.view.user.UserRegisterActivity

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.activity_settings_fragment, null)
        val btnLogin =
            view.findViewById<Button>(R.id.btnLogin)
        val btnRegister =
            view.findViewById<Button>(R.id.btnRegister)
        btnLogin.setOnClickListener {
            val intent = Intent(context, UserLoginActivity::class.java)
            startActivity(intent)
        }
        btnRegister.setOnClickListener {
            val intent = Intent(context, UserRegisterActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}