package com.capgemini.dmart_assignment.utils

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.dmart_assignment.application.UserApplication
import com.capgemini.dmart_assignment.model.entities.UserEntity
import kotlinx.coroutines.CoroutineScope

class Utils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val service = Context.CONNECTIVITY_SERVICE
            val manager = context.getSystemService(service) as ConnectivityManager?
            val network = manager?.activeNetwork

            return (network != null)
        }

        fun getLayoutManager(context: Context): LinearLayoutManager {
            return LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        private fun getSharedPreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(Constants.SHAREDPREFRENCES_NAME, MODE_PRIVATE)
        }

        fun setOrderBy(context: Context, value: String) {
            getSharedPreferences(context)
                .edit()
                .putString(Constants.PREF_ORDER_BY, value)
                .apply()
        }

        fun getOrderBy(context: Context): String {
            return getSharedPreferences(context).getString(Constants.PREF_ORDER_BY, "id")!!
        }

        fun setOrder(context: Context, value: String) {
            getSharedPreferences(context)
                .edit()
                .putString(Constants.PREF_ORDER, value)
                .apply()
        }

        fun getOrder(context: Context): String {
            return getSharedPreferences(context).getString(Constants.PREF_ORDER, Constants.ORDER_ASCENDING)!!
        }

        fun sortUserListByFirstName(list: ArrayList<UserEntity>,isAscending:Boolean): ArrayList<UserEntity> {
            list.sortWith { lhs, rhs ->
                if (lhs.firstName > rhs.firstName) -1 else if (lhs.firstName < rhs.firstName) 1 else 0
            }

            if (isAscending) list.reverse()

            return list
        }

        fun sortUserListBylastName(list: ArrayList<UserEntity>,isAscending:Boolean): ArrayList<UserEntity> {
            list.sortWith { lhs, rhs ->
                if (lhs.lastName > rhs.lastName) -1 else if (lhs.lastName < rhs.lastName) 1 else 0
            }

            if (isAscending) list.reverse()

            return list
        }
    }
}