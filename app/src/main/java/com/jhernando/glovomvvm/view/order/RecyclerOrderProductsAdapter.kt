package com.jhernando.glovomvvm.view.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.BR
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.OrderProductViewModel
import com.jhernando.glovomvvm.viewmodel.ProductViewModel

class RecyclerOrderProductsAdapter(var orderProductViewModel: OrderProductViewModel, var resource: Int) : RecyclerView.Adapter<RecyclerOrderProductsAdapter.CardOrderProductHolder>() {

    var orderProducts: List<Product>? = null

    fun setOrderProductsList(orderProducts: List<Product>?) {
        this.orderProducts = orderProducts
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardOrderProductHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardOrderProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderProducts?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardOrderProductHolder, p1: Int) {
        p0.setDataCard(orderProductViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int {
        return resource
    }

    class CardOrderProductHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(orderProductViewModel: OrderProductViewModel, position: Int) {
            binding?.setVariable(BR.model, orderProductViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }
    }
}
