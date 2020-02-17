package com.jhernando.glovomvvm.view.business

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.jhernando.glovomvvm.BR
import com.jhernando.glovomvvm.model.business.Business
import com.jhernando.glovomvvm.model.business.Product
import com.jhernando.glovomvvm.viewmodel.BusinessViewModel

class RecyclerBusinessAdapter(var businessViewModel: BusinessViewModel, var resource: Int) : RecyclerView.Adapter<RecyclerBusinessAdapter.CardBusinessHolder>(){

    var businesses: List<Business>? = null

    fun setBusinessesList(businesses: List<Business>?){
        this.businesses = businesses
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardBusinessHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardBusinessHolder(binding)
    }

    override fun getItemCount(): Int {
        return businesses?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardBusinessHolder, p1: Int) {
        p0.setDataCard(businessViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }

    class CardBusinessHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(businessViewModel: BusinessViewModel, position: Int){
            binding?.setVariable(BR.model, businessViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }
    }
}