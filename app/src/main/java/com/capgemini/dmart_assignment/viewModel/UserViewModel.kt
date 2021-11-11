package com.capgemini.dmart_assignment.viewModel

import androidx.lifecycle.*
import com.capgemini.dmart_assignment.model.db.UserRepository
import com.capgemini.dmart_assignment.model.entities.UserEntity
import com.capgemini.dmart_assignment.model.network.UserApiService
import com.capgemini.dmart_assignment.model.network.UserResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class UserViewModel(val userRepository: UserRepository) : ViewModel() {

    private val userApiService = UserApiService()

    val userResponse = MutableLiveData<List<UserEntity>>()
    val userLoadingError = MutableLiveData<Boolean>()
    var usersDb: LiveData<List<UserEntity>> = userRepository.allUsersList.asLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun getUsersFromAPI() {
        compositeDisposable.add(
            userApiService.getUsersList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<UserResponse>() {
                    override fun onSuccess(value: UserResponse) {
                        userResponse.value = value.userList
                        userLoadingError.value = true
                    }

                    override fun onError(e: Throwable) {
                        userLoadingError.value = false
                    }
                })
        )
    }

    fun cacheUserData(userList: ArrayList<UserEntity>) {
        viewModelScope.launch {
            userRepository.clearTable()
            userRepository.insertUsersList(userList)
        }
    }

    fun deleteUserData(user: UserEntity) {
        viewModelScope.launch {
            userRepository.deleteUsersData(user)
        }
    }
}

class UserViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}