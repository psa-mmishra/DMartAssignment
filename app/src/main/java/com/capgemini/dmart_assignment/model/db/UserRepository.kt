package com.capgemini.dmart_assignment.model.db

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.RoomSQLiteQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.capgemini.dmart_assignment.model.entities.UserEntity
import com.capgemini.dmart_assignment.utils.Constants
import com.capgemini.dmart_assignment.utils.Utils
import kotlinx.coroutines.flow.Flow


class UserRepository(private val userDao: UserDao, private val context: Context) {

    @WorkerThread
    suspend fun insertUsersList(users: ArrayList<UserEntity>) {
        userDao.insertUsersList(users)
    }

    var allUsersList: Flow<List<UserEntity>> = userDao.getAllUsersList()

    @WorkerThread
    suspend fun deleteUsersData(user: UserEntity) {
        userDao.deleteUserDetails(user)
    }

    @WorkerThread
    suspend fun clearTable() {
        userDao.clearTable()
    }
}