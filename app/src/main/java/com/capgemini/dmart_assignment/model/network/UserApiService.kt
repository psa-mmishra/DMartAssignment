package com.capgemini.dmart_assignment.model.network

import com.capgemini.dmart_assignment.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UserApiService {
    private val api = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(UserAPI::class.java)

    fun getUsersList(): Single<UserResponse> {
        return api.getUsers()
    }
}