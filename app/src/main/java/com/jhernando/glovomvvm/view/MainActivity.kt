package com.jhernando.glovomvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.view.fragments.HomeFragment
import com.jhernando.glovomvvm.view.fragments.SettingsFragment
import com.jhernando.glovomvvm.view.fragments.UserSettingsFragment

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigation = findViewById<BottomNavigationView>(R.id.NavBot)
        navigation.setOnNavigationItemSelectedListener(this)
        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment).commit()
            return true
        }
        return false
    }

    override fun onNavigationItemSelected(@NonNull menuItem: MenuItem): Boolean {
        var fragment: Fragment? = null
        val userDetails = getSharedPreferences("userdetails", MODE_PRIVATE)
        val userId = userDetails.getInt("userId", 0)
        when (menuItem.getItemId()) {
            R.id.navigation_home -> fragment = HomeFragment()
            R.id.navigation_orders -> if (userId > 0) {
                fragment = OrdersActivity()
            } else {
                Toast.makeText(this, "Cuenta necesaria para ir a Tus pedidos!", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            R.id.navigation_profile -> if (userId > 0) {
                fragment = UserProfileActivity()
            } else {
                Toast.makeText(this, "Cuenta necesaria para ir a Tu perfil!", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
            R.id.navigation_settings -> if (userId > 0) {
                fragment = UserSettingsFragment()
            } else {
                fragment = SettingsFragment()
            }
        }
        return loadFragment(fragment!!)
    }
}
