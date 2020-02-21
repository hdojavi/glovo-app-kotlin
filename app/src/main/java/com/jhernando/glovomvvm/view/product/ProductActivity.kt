package com.jhernando.glovomvvm.view.product

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityProductsBinding
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_products.*

class ProductActivity : AppCompatActivity() {
    private var userDetails: SharedPreferences? = null
    private var thisBusiness: Business? = null

    private var productViewModel: ProductViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        arrowBackProducts.setOnClickListener({ finish() })

        userDetails = getSharedPreferences("userdetails", Context.MODE_PRIVATE)

        buttonOrders.setOnClickListener(View.OnClickListener {
            if (userDetails!!.getInt("userId", 0) > 0) {
                /*val intent =
                    Intent(applicationContext, orderProductsActivity::class.java)
                intent.putExtra("cart", productCart)
                intent.putExtra("business", thisBusiness)
                startActivity(intent)*/
            } else {
                Toast.makeText(
                    applicationContext,
                    "Necesitas una cuenta para pedir",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        val intent = intent
        thisBusiness = intent.getSerializableExtra("business") as Business
        initCards()
        setupBindings(savedInstanceState)
    }


    fun setupBindings(savedInstanceState: Bundle?) {
        var activityMainBinding: ActivityProductsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_products)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        activityMainBinding.setModel(productViewModel)
        setupListUpdate()

    }

    fun setupListUpdate() {
        productViewModel?.callProducts(thisBusiness!!.id)

        productViewModel?.getProducts()?.observe(this, Observer { products: List<Product> ->
            productViewModel?.setProductInRecyclerAdapter(products)
        })
    }

    private fun initCards() {
        searchQuery.setHint("Buscar en " + thisBusiness!!.name + "...")
        nameCardBusiness.text = thisBusiness!!.name
        val shippingPrice = thisBusiness!!.shippingprice.toString().replace('.', ',') + "€"
        priceCardBusiness.text = shippingPrice
        rateCardBusiness.text = Integer.toString(thisBusiness!!.rate) + "%"
        val kmAway = thisBusiness!!.kmaway / 1000
        if (kmAway > 1) {
            kmCardBusiness.text = kmAway.toString() + "km"
        } else {
            kmCardBusiness.text = thisBusiness!!.kmaway.toString() + "m"
        }
        Picasso.with(this).load(thisBusiness!!.thumb).into(imgBusinessProductDetail)
    }

}