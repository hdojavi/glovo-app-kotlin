package com.jhernando.glovomvvm.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.R
import java.util.*

class OrdersActivity : Fragment(){
    /*private var view: View? = null
    private var userDetails: SharedPreferences? = null
    private var orderPresenter: ordersPresenter? = null
    private var orderRecycler: RecyclerView? = null
    private var orders: ArrayList<Order>? = null
    private var ordersAdapter: OrdersAdapter? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.activity_order_fragment, null)
        orderPresenter = ordersPresenter(this)
        initUI()
        userDetails =
            context!!.getSharedPreferences("userdetails", Context.MODE_PRIVATE)
        orderPresenter.checkOrders(userDetails.getInt("userId", 0))
        return view
    }

    private fun initUI() {
        orderRecycler = view!!.findViewById(R.id.OrdersRecycler)
        orders = ArrayList<Order>()
        ordersAdapter = OrdersAdapter(this, orders)
        mLayoutManager = LinearLayoutManager(context)
        orderRecycler.setLayoutManager(mLayoutManager)
        orderRecycler.setItemAnimator(DefaultItemAnimator())
        orderRecycler.setAdapter(ordersAdapter)
    }

    fun successOrders(orders: ArrayList<Order?>?) {
        this.orders!!.addAll(orders!!)
        ordersAdapter.notifyDataSetChanged()
    }

    fun failureOrders(t: Throwable?) {
        Toast.makeText(context, "Error en los pedidos", Toast.LENGTH_SHORT).show()
    }

    fun onOrderItemClick(position: Int) {
        val order: Order = orders!![position]
        val intent = Intent(context, orderLinesActivity::class.java)
        intent.putExtra("order", order)
        startActivity(intent)
    }*/
}