package com.jhernando.glovomvvm.view.order

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityOrderDetailsBinding
import com.jhernando.glovomvvm.model.business.OrderDetail
import com.jhernando.glovomvvm.viewmodel.OrderDetailViewModel
import kotlinx.android.synthetic.main.activity_order_details.*

class OrderDetailActivity : AppCompatActivity() {
    private var userDetails: SharedPreferences? = null

    private var id: Int? = null
    private var orderDetailViewModel: OrderDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

        userDetails = getSharedPreferences("userdetails", Context.MODE_PRIVATE)

        numTlfnDetail.setText(userDetails!!.getString("tlfn", null))

        val intent = intent
        id = intent.getIntExtra("id", 0)

        setupBindings(savedInstanceState)
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityOrderDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_order_details)

        orderDetailViewModel = ViewModelProvider(this).get(OrderDetailViewModel::class.java)

        activityMainBinding.setModel(orderDetailViewModel)

        setupListUpdate()
    }

    fun setupListUpdate() {
        orderDetailViewModel?.callOrderDetail(id!!)

        orderDetailViewModel?.getOrderDetail()
            ?.observe(this, Observer { orders: List<OrderDetail> ->
                orderDetailViewModel?.setOrderDetailInRecyclerAdapter(orders)
            })
    }

}