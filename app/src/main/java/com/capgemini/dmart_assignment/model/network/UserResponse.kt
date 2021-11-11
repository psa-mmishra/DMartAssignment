package com.capgemini.dmart_assignment.model.network

import com.capgemini.dmart_assignment.model.entities.UserEntity
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("page")
    val page:Int,
    @SerializedName("per_page")
    val per_page:Int,
    @SerializedName("data")
    val userList: List<UserEntity>
)
