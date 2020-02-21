package com.jhernando.glovomvvm.view.order

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonParser
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityOrderProductsBinding
import com.jhernando.glovomvvm.model.business.Order
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.OrderProductViewModel
import com.jhernando.glovomvvm.viewmodel.OrderViewModel
import com.jhernando.glovomvvm.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_order_products.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.set

class OrderProductsActivity : AppCompatActivity() {
    private var productCart: ArrayList<Product> = ArrayList()
    private var orderProductViewModel: OrderProductViewModel? = null
    private var newCart: ArrayList<Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_products)

        val intent = intent
        productCart = intent.getSerializableExtra("cart") as ArrayList<Product>

        newCart = arrayNoDuplicates(productCart)!!

        setupBindings(savedInstanceState)
        ProductsOrderRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
    }

    private fun arrayNoDuplicates(cart: ArrayList<Product>): ArrayList<Product>? {
        val newCart = ArrayList<Product>()
        val noDuplicates: MutableMap<Int, Int?> = HashMap()
        var i = -1
        for (p in cart) {
            if (noDuplicates.containsKey(p.id)) {
                var productJson =
                    JsonParser().parse(
                        "{\"id\": " + p.id + ", " +
                                "\"name\": '" + p.name + "', " +
                                "\"description\": '" + p.description + "', " +
                                "\"price\": " + p.price + ", " +
                                "\"quantity\": " + 0 + ", " +
                                "\"businessid\": " + p.business!!.id + "}"
                    )
                        .asJsonObject
                val product = Product(productJson)
                product.price = product.price!! + newCart[noDuplicates[p.id]!!].price!!
                product.quantity = (newCart[noDuplicates[p.id]!!].quantity!! + 1)
                newCart[noDuplicates[p.id]!!] = product
            } else {
                i++
                noDuplicates[p.id!!] = i
                var productJson =
                    JsonParser().parse(
                        "{\"id\": " + p.id!! + ", " +
                                "\"name\": '" + p.name!! + "', " +
                                "\"description\": '" + p.description!! + "', " +
                                "\"price\": " + p.price!! + ", " +
                                "\"quantity\": 0, " +
                                "\"businessid\": " + p.business!!.id + "}"
                    )
                        .asJsonObject
                val product = Product(productJson)
                product.quantity = 1
                newCart.add(product)
            }
        }
        return newCart
    }

    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityOrderProductsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_order_products)

        orderProductViewModel = ViewModelProvider(this).get(OrderProductViewModel::class.java)

        activityMainBinding.setModel(orderProductViewModel)

        setupListUpdate()
    }

    fun setupListUpdate() {
        orderProductViewModel?.callOrderProduct(newCart)

        orderProductViewModel?.getOrderProduct()
            ?.observe(this, Observer { orders: List<Product> ->
                orderProductViewModel?.setOrderProductInRecyclerAdapter(orders)
            })
    }

}