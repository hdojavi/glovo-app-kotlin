package com.jhernando.glovomvvm.model.business

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.jhernando.glovomvvm.model.ApiAdapter
import com.jhernando.glovomvvm.model.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl :
    UserRepository {

    private var user = MutableLiveData<User>()

    override fun getUser(): MutableLiveData<User> {
        return user;
    }

    override fun callUserAPI(userObject: User) {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getUser(userObject)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(
                call: Call<User>,
                response: Response<User>
            ) {
                user.value = response.body()
            }
        })
    }
}

