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
import androidx.lifecycle.ViewModelProviders
import com.jhernando.glovomvvm.R
import com.jhernando.glovomvvm.databinding.ActivityProductsBinding
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.ProductViewModel
import com.squareup.picasso.Picasso

class ProductActivity : AppCompatActivity() {
    private var userDetails: SharedPreferences? = null
    private var thisBusiness: Business? = null

    private var btnBack: ImageView? = null
    private var textSearch: TextView? = null
    private var nameCard: TextView? = null
    private var priceCard: TextView? = null
    private var rateCard: TextView? = null
    private var kmCard: TextView? = null
    private var imgBusinessProduct: ImageView? = null
    private var searchBtn: ImageView? = null
    private var searchQuery: TextView? = null
    private var buttonOrder: Button? = null

    private var productViewModel: ProductViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        btnBack = findViewById(R.id.arrowBack)
        textSearch = findViewById(R.id.searchQuery)
        nameCard = findViewById(R.id.nameCard)
        priceCard = findViewById(R.id.priceCard)
        rateCard = findViewById(R.id.rateCard)
        kmCard = findViewById(R.id.kmCard)
        imgBusinessProduct = findViewById(R.id.imgBusinessProduct)
        searchBtn = findViewById(R.id.buttonSearch)
        searchQuery = findViewById(R.id.searchQuery)
        buttonOrder = findViewById(R.id.buttonOrders)
        btnBack!!.setOnClickListener(View.OnClickListener { finish() })
        userDetails = getSharedPreferences("userdetails", Context.MODE_PRIVATE)

        btnBack!!.setOnClickListener(View.OnClickListener { finish() })
        buttonOrder!!.setOnClickListener(View.OnClickListener {
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

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)

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
        textSearch!!.hint = "Buscar en " + thisBusiness!!.name.toString() + "..."
        nameCard!!.setText(thisBusiness!!.name)
        val shippingPrice = (thisBusiness!!.shippingprice.toString()).replace('.', ',') + "€"
        priceCard!!.text = shippingPrice
        rateCard!!.setText(Integer.toString(thisBusiness!!.rate) + "%")
        val kmAway = thisBusiness!!.kmaway as Double / 1000
        if (kmAway > 1) {
            kmCard!!.text = kmAway.toString() + "km"
        } else {
            kmCard!!.setText(thisBusiness!!.kmaway.toString() + "m")
        }
        Picasso.with(this).load(thisBusiness!!.thumb).into(imgBusinessProduct)
        imgBusinessProduct!!.setColorFilter(
            Color.rgb(130, 130, 130),
            PorterDuff.Mode.MULTIPLY
        )
    }

}