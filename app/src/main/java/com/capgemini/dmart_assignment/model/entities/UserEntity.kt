package com.capgemini.dmart_assignment.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.capgemini.dmart_assignment.utils.Constants.Companion.DB_TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = DB_TABLE_NAME)
data class UserEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo val email: String,
    @ColumnInfo(name = "first_name") @SerializedName("first_name") val firstName: String,
    @ColumnInfo(name = "last_name") @SerializedName("last_name")  val lastName: String,
    @ColumnInfo val avatar: String,
)