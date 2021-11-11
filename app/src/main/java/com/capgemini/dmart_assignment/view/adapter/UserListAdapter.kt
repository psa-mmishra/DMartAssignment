package com.capgemini.dmart_assignment.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capgemini.dmart_assignment.R
import com.capgemini.dmart_assignment.databinding.ItemUserListDetailsBinding
import com.capgemini.dmart_assignment.model.entities.UserEntity
import com.capgemini.dmart_assignment.utils.Utils
import com.capgemini.dmart_assignment.view.activity.MainActivity

class UserListAdapter(private val activity: Activity, private val listItems: ArrayList<UserEntity>) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    private val SORT_FIRSTNAME_ASC = 0
    private val SORT_FIRSTNAME_DESC = 1
    private val SORT_LASTNAME_ASC = 2
    private val SORT_LASTNAME_DESC = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding: ItemUserListDetailsBinding =
            ItemUserListDetailsBinding.inflate(LayoutInflater.from(activity), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = listItems[position]

        val name = "${user.firstName} ${user.lastName}"
        holder.name.text = name
        holder.email.text = user.email

        Glide
            .with(activity)
            .load(user.avatar)
            .placeholder(R.drawable.ic_placeholder)
            .into(holder.avatar)

        holder.btnDelete.setOnClickListener {
            if (activity is MainActivity)
                activity.deleteEntry(user)

        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun sortData(sortBy: Int) {
        when (sortBy) {
            SORT_FIRSTNAME_ASC -> {
                Utils.sortUserListByFirstName(listItems, true)
            }
            SORT_FIRSTNAME_DESC -> {
                Utils.sortUserListByFirstName(listItems, false)
            }
            SORT_LASTNAME_ASC -> {
                Utils.sortUserListBylastName(listItems, true)
            }
            SORT_LASTNAME_DESC -> {
                Utils.sortUserListBylastName(listItems, false)
            }
        }

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listItems.size

    class UserListViewHolder(itemView: ItemUserListDetailsBinding) : RecyclerView.ViewHolder(itemView.root) {
        val name = itemView.tvUserName
        val email = itemView.tvUserEmail
        val avatar = itemView.imgUserAvatar
        val btnDelete = itemView.imgDelete
    }
}