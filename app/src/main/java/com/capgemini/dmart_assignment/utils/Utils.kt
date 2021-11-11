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