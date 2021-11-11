package com.capgemini.dmart_assignment.model.network

import com.capgemini.dmart_assignment.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface UserAPI {
    @GET(Constants.API_ENDPOINT)
    fun getUsers():Single<UserResponse>
}