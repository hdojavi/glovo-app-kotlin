package com.jhernando.glovomvvm.model.business

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class BusinessObservable: BaseObservable(){

    private var businessRepository: BusinessRepository =
        BusinessRepositoryImpl()

    fun callBusinesses(id: Int){
        businessRepository.callBusinessAPI(id)
    }

    fun getBusinesses() : MutableLiveData<List<Business>>{
        return businessRepository.getBusiness()
    }
}