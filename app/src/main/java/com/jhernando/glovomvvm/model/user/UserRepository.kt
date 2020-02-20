package com.jhernando.glovomvvm.model.business

import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.user.User

interface UserRepository {
    fun getUser(): MutableLiveData<User>
    fun callUserAPI(user: User)
}