package com.jhernando.glovomvvm.view.order

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityOrderFragmentBinding
import com.jhernando.glovomvvm.databinding.ActivityProductsBinding
import com.jhernando.glovomvvm.model.business.Order
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.OrderViewModel
import com.jhernando.glovomvvm.viewmodel.ProductViewModel

class OrdersActivity : Fragment() {
    private var viewOrder: View? = null
    private var userDetails: SharedPreferences? = null

    private var orderViewModel: OrderViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewOrder = inflater.inflate(R.layout.activity_order_fragment, null)

        userDetails = viewOrder!!.context.getSharedPreferences("userdetails", Context.MODE_PRIVATE)

        setupBindings(savedInstanceState)

        return viewOrder
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityOrderFragmentBinding =
            DataBindingUtil.setContentView(activity as Activity, R.layout.activity_order_fragment)

        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        activityMainBinding.setModel(orderViewModel)
        setupListUpdate()

    }

    fun setupListUpdate() {
        orderViewModel?.callOrders(userDetails!!.getInt("id",0))

        orderViewModel?.getOrders()?.observe(viewLifecycleOwner, Observer { orders: List<Order> ->
            orderViewModel?.setOrderInRecyclerAdapter(orders)
        })
    }
    /*fun onOrderItemClick(position: Int) {
        val order: Order = orders!![position]
        val intent = Intent(context, OrderLinesActivity::class.java)
        intent.putExtra("order", order)
        startActivity(intent)
    }*/
}