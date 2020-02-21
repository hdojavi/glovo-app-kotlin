package com.jhernando.glovomvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.model.business.OrderProductObservable
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.view.order.RecyclerOrderProductsAdapter

class OrderProductViewModel: ViewModel() {
    private var orderProductObservable: OrderProductObservable =
        OrderProductObservable()
    private var recyclerOrderProductsAdapter: RecyclerOrderProductsAdapter? = null

    fun callOrderProduct(cart: ArrayList<Product>) {
        orderProductObservable.callOrderProducts(cart)
    }

    fun getOrderProduct(): MutableLiveData<List<Product>> {
        return orderProductObservable.getOrderProducts()
    }

    fun setOrderProductInRecyclerAdapter(orders: List<Product>) {
        recyclerOrderProductsAdapter?.setOrderProductsList(orders)
        recyclerOrderProductsAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerOrderProductAdapter(): RecyclerOrderProductsAdapter? {
        recyclerOrderProductsAdapter =
            RecyclerOrderProductsAdapter(this, R.layout.card_orderproducts)
        return recyclerOrderProductsAdapter
    }

    fun getOrderProductsAt(position: Int): Product? {
        var orders: List<Product>? = orderProductObservable.getOrderProducts().value
        return orders?.get(position)
    }
}