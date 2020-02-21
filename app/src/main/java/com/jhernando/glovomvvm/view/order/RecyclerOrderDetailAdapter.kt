package com.jhernando.glovomvvm.view.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.BR
import com.jhernando.glovomvvm.model.business.OrderDetail
import com.jhernando.glovomvvm.viewmodel.OrderDetailViewModel

class RecyclerOrderDetailAdapter(var orderDetailViewModel: OrderDetailViewModel, var resource: Int) : RecyclerView.Adapter<RecyclerOrderDetailAdapter.CardOrderDetailHolder>() {

    var orderDetails: List<OrderDetail>? = null

    fun setOrdersList(orderDetails: List<OrderDetail>?) {
        this.orderDetails = orderDetails
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardOrderDetailHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardOrderDetailHolder(binding)
    }

    override fun getItemCount(): Int {
        return orderDetails?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardOrderDetailHolder, p1: Int) {
        p0.setDataCard(orderDetailViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int {
        return resource
    }

    class CardOrderDetailHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(orderDetailViewModel: OrderDetailViewModel, position: Int) {
            binding?.setVariable(BR.model, orderDetailViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }
    }
}