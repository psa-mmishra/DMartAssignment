package com.capgemini.dmart_assignment.model.db

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.capgemini.dmart_assignment.model.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersList(users: ArrayList<UserEntity>)

    @Query("SELECT * FROM TABLE_USER_NAME")
    fun getAllUsersList(): Flow<List<UserEntity>>

    @Query("SELECT * FROM table_user_name ORDER BY first_name")
    fun getAllUsersListByFirstNameASC(): Flow<List<UserEntity>>

    @Query("SELECT * FROM table_user_name ORDER BY first_name DESC")
    fun getAllUsersListByFirstNameDESC(): Flow<List<UserEntity>>

    @Query("SELECT * FROM table_user_name ORDER BY last_name")
    fun getAllUsersListByLastNameASC(): Flow<List<UserEntity>>

    @Query("SELECT * FROM table_user_name ORDER BY last_name DESC")
    fun getAllUsersListByLastNameDESC(): Flow<List<UserEntity>>

    @Delete
    suspend fun deleteUserDetails(user: UserEntity)

    @Query("DELETE from table_user_name")
    suspend fun clearTable()
}