package com.jhernando.glovomvvm.view.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.BR
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.Order
import com.jhernando.glovomvvm.viewmodel.BusinessViewModel
import com.jhernando.glovomvvm.viewmodel.OrderViewModel

class RecyclerOrderAdapter(var orderViewModel: OrderViewModel, var resource: Int) : RecyclerView.Adapter<RecyclerOrderAdapter.CardOrderHolder>() {

    var orders: List<Order>? = null

    fun setOrdersList(orders: List<Order>?) {
        this.orders = orders
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardOrderHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardOrderHolder(binding)
    }

    override fun getItemCount(): Int {
        return orders?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardOrderHolder, p1: Int) {
        p0.setDataCard(orderViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int {
        return resource
    }

    class CardOrderHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(orderViewModel: OrderViewModel, position: Int) {
            binding?.setVariable(BR.model, orderViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }
    }
}
