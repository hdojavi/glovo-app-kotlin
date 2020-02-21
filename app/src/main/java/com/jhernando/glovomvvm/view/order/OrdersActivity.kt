package com.jhernando.glovomvvm.view.order

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityOrderFragmentBinding
import com.jhernando.glovomvvm.model.business.Order
import com.jhernando.glovomvvm.viewmodel.OrderViewModel


class OrdersActivity : AppCompatActivity() {
    private var userDetails: SharedPreferences? = null

    private var orderViewModel: OrderViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_fragment)

        userDetails = getSharedPreferences("userdetails", Context.MODE_PRIVATE)

        setupBindings(savedInstanceState)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityOrderFragmentBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_order_fragment)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        activityMainBinding.setModel(orderViewModel)

        setupListUpdate()
    }

    fun setupListUpdate() {
        var idUser = userDetails!!.getInt("userId", 0);
        orderViewModel?.callOrders(idUser)

        orderViewModel?.getOrders()
            ?.observe(this, Observer { orders: List<Order> ->
                orderViewModel?.setOrderInRecyclerAdapter(orders)
            })
    }

}