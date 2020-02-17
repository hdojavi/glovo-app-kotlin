package com.jhernando.glovomvvm.view.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.BR
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.ProductViewModel

class RecyclerProductAdapter(var productViewModel: ProductViewModel, var resource: Int) : RecyclerView.Adapter<RecyclerProductAdapter.CardProductHolder>(){

    var products: List<Product>? = null

    fun setProductsList(products: List<Product>?){
        this.products = products
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardProductHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardProductHolder, p1: Int) {
        p0.setDataCard(productViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }

    class CardProductHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(productsViewModel: ProductViewModel, position: Int){
            binding?.setVariable(BR.model, productsViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }
    }
}