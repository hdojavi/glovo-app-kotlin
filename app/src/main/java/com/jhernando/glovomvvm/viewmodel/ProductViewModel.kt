package com.jhernando.glovomvvm.viewmodel

import android.content.Context
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.button.MaterialButton
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.model.business.ProductObservable
import com.jhernando.glovomvvm.view.order.OrderProductsActivity
import com.jhernando.glovomvvm.view.product.ProductActivity
import com.jhernando.glovomvvm.view.product.RecyclerProductAdapter
import kotlinx.android.synthetic.main.activity_products.view.*
import kotlinx.android.synthetic.main.card_products.view.*

class ProductViewModel : ViewModel() {

    private var productObservable: ProductObservable = ProductObservable()
    private var recyclerProductAdapter: RecyclerProductAdapter? = null
    private var productCart: ArrayList<Product>? = ArrayList<Product>()

    fun callProducts(id: Int) {
        productObservable.callProducts(id)
    }

    fun getProducts(): MutableLiveData<List<Product>> {
        return productObservable.getProducts()
    }

    fun setProductInRecyclerAdapter(products: List<Product>) {
        recyclerProductAdapter?.setProductsList(products)
        recyclerProductAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerProductAdapter(): RecyclerProductAdapter? {
        recyclerProductAdapter = RecyclerProductAdapter(this, R.layout.card_products)
        return recyclerProductAdapter
    }

    fun getProductsAt(position: Int): Product? {
        var products: List<Product>? = productObservable.getProducts().value
        return products?.get(position)
    }

    fun addProductsItemClick(
        position: Int, buttonRemove: ImageView, textProduct: TextView
    ) {
        productCart!!.add(getProductsAt(position)!!)
        var quantity = 0
        for (p in productCart!!) {
            if (p.id === getProductsAt(position)!!.id) {
                textProduct.text = (++quantity).toString() + "x"
            }
        }
        buttonRemove.visibility = View.VISIBLE
        var quantities = 0
        var price = 0.0
        for (p in productCart!!) {
            quantities++
            price += p.price!!
        }
    }

    fun removeProductsItemClick(
        position: Int,
        removeProduct: ImageView,
        numberProduct: TextView
    ) {
        productCart!!.remove(getProductsAt(position)!!)
        if (numberProduct.text.toString() == "1x") {
            numberProduct.text = ""
            removeProduct.visibility = View.INVISIBLE
        } else {
            var number = Integer.valueOf(
                numberProduct.text.toString().substring(
                    0,
                    numberProduct.text.length - 1
                )
            ) - 1
            numberProduct.text = number.toString() + "x"
        }
        if (!productCart!!.isEmpty()) {
            var quantities = 0
            var price = 0.0
            for (p in productCart!!) {
                quantities++
                price += p.price!!
            }
        }
    }

    fun goToOrderDetail(view: View){
        var context: Context? = view.context
        val intent = Intent(context, OrderProductsActivity::class.java)
        intent.putExtra("cart", productCart)
        context!!.startActivity(intent)
    }
}