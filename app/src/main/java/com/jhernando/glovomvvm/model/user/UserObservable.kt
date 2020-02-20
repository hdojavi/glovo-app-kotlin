package com.jhernando.glovomvvm.model.business

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.user.User

class UserObservable: BaseObservable(){

    private var userRepository: UserRepository =
        UserRepositoryImpl()

    fun callUser(user: User){
        userRepository.callUserAPI(user)
    }

    fun getUser() : MutableLiveData<User>{
        return userRepository.getUser()
    }
}