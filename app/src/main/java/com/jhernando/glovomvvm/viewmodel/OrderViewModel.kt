package com.jhernando.glovomvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.BusinessObservable
import com.jhernando.glovomvvm.model.business.Order
import com.jhernando.glovomvvm.model.business.OrderObservable
import com.jhernando.glovomvvm.view.business.RecyclerBusinessAdapter
import com.jhernando.glovomvvm.view.order.RecyclerOrderAdapter
import com.jhernando.glovomvvm.view.product.ProductActivity


class OrderViewModel : ViewModel() {

    private var orderObservable: OrderObservable =
        OrderObservable()
    private var recyclerOrderAdapter: RecyclerOrderAdapter? = null

    fun callOrders(id: Int) {
        orderObservable.callOrders(id)
    }

    fun getOrders(): MutableLiveData<List<Order>> {
        return orderObservable.getOrders()
    }

    fun setOrderInRecyclerAdapter(orders: List<Order>) {
        recyclerOrderAdapter?.setOrdersList(orders)
        recyclerOrderAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerOrderAdapter(): RecyclerOrderAdapter? {
        recyclerOrderAdapter = RecyclerOrderAdapter(this, R.layout.activity_order_fragment)
        return recyclerOrderAdapter
    }

    fun getOrderAt(position: Int): Order? {
        var orders: List<Order>? = orderObservable.getOrders().value
        return orders?.get(position)
    }

    fun goToOrder(view: View, order: Order){
        var context: Context? = view.context
        val intent = Intent(context, ProductActivity::class.java)
        intent.putExtra("id", order.id)
        context!!.startActivity(intent)
    }
}