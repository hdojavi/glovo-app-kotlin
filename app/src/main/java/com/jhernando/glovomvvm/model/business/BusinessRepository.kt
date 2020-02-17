package com.jhernando.glovomvvm.model.business

import androidx.lifecycle.MutableLiveData

interface BusinessRepository {
    fun getBusiness(): MutableLiveData<List<Business>>
    fun callBusinessAPI(id: Int)
}