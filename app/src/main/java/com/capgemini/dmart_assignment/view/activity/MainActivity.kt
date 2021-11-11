package com.capgemini.dmart_assignment.view.activity

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capgemini.dmart_assignment.R
import com.capgemini.dmart_assignment.application.UserApplication
import com.capgemini.dmart_assignment.databinding.ActivityMainBinding
import com.capgemini.dmart_assignment.model.entities.UserEntity
import com.capgemini.dmart_assignment.utils.Constants
import com.capgemini.dmart_assignment.utils.Utils
import com.capgemini.dmart_assignment.view.adapter.UserListAdapter
import com.capgemini.dmart_assignment.viewModel.UserViewModel
import com.capgemini.dmart_assignment.viewModel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userViewModel: UserViewModel

    private lateinit var userListAdapter: UserListAdapter
    private var userList = ArrayList<UserEntity>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this, UserViewModelFactory((application as UserApplication).repository)).get(UserViewModel::class.java)

        setObservers()
        userListAdapter = UserListAdapter(this@MainActivity, userList)
        binding.recyclerUserList.apply {
            layoutManager = Utils.getLayoutManager(this@MainActivity)
            adapter = userListAdapter
        }

        binding.fabFilter.setOnClickListener {
            val builder = AlertDialog.Builder(this)
                .setTitle(getString(R.string.sort_title))
                .setItems(resources.getStringArray(R.array.sort_options)) { dialog, which ->
                    userListAdapter.sortData(which)
                    dialog.dismiss()
                }
            builder.create().show()
        }

        if (Utils.isNetworkAvailable(this))
            userViewModel.getUsersFromAPI()
    }

    private fun setObservers() {
        userViewModel.usersDb.observe(this,
            { users ->
                users?.let {
                    userList.clear()
                    userList.addAll(users)
                    binding.recyclerUserList.adapter?.notifyDataSetChanged()
                }
            })

        userViewModel.userResponse.observe(this,
            { userResponse ->
                userResponse?.let { users ->
                    userViewModel.cacheUserData(ArrayList(users))
                }
            })

        userViewModel.userLoadingError.observe(this,
            { dataError ->
                dataError?.let {
                    Log.i("API Error", "-------------$dataError")
                }
            })
    }

    fun deleteEntry(user: UserEntity) {
        userViewModel.deleteUserData(user)
    }
}