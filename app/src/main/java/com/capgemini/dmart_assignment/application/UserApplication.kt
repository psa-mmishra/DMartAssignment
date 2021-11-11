package com.capgemini.dmart_assignment.application

import android.app.Application
import com.capgemini.dmart_assignment.model.db.UserRepository
import com.capgemini.dmart_assignment.model.db.UserRoomDatabase

class UserApplication : Application() {
    private val database by lazy { UserRoomDatabase.getDatabase(this@UserApplication) }
    val repository by lazy { UserRepository(database.userDao(),this@UserApplication) }
}