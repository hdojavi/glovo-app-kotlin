package com.jhernando.glovomvvm.view.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.view.MainActivity

class UserSettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View =
            inflater.inflate(R.layout.activity_usersettings_fragment, null)
        val btnLogout =
            view.findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val userDetails = context!!.getSharedPreferences(
                "userdetails",
                Context.MODE_PRIVATE
            )
            val editor = userDetails.edit()
            editor.clear().commit()
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}