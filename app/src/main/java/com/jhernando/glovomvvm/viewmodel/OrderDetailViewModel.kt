package com.jhernando.glovomvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.model.business.*
import com.jhernando.glovomvvm.view.business.RecyclerBusinessAdapter
import com.jhernando.glovomvvm.view.order.OrderDetailActivity
import com.jhernando.glovomvvm.view.order.RecyclerOrderAdapter
import com.jhernando.glovomvvm.view.order.RecyclerOrderDetailAdapter
import com.jhernando.glovomvvm.view.product.ProductActivity


class OrderDetailViewModel : ViewModel() {

    private var orderDetailObservable: OrderDetailObservable =
        OrderDetailObservable()
    private var recyclerOrderDetailAdapter: RecyclerOrderDetailAdapter? = null

    fun callOrderDetail(id: Int) {
        orderDetailObservable.callOrderDetails(id)
    }

    fun getOrderDetail(): MutableLiveData<List<OrderDetail>> {
        return orderDetailObservable.getOrderDetails()
    }

    fun setOrderDetailInRecyclerAdapter(orderDetail: List<OrderDetail>) {
        recyclerOrderDetailAdapter?.setOrdersList(orderDetail)
        recyclerOrderDetailAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerOrderDetailAdapter(): RecyclerOrderDetailAdapter? {
        recyclerOrderDetailAdapter = RecyclerOrderDetailAdapter(this, R.layout.card_orderdetails)
        return recyclerOrderDetailAdapter
    }

    fun getOrderDetailAt(position: Int): OrderDetail? {
        var orderDetails: List<OrderDetail>? = orderDetailObservable.getOrderDetails().value
        return orderDetails?.get(position)
    }

}