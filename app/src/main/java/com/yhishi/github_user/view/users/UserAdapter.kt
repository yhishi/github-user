package com.yhishi.github_user.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.squareup.picasso.Picasso
import com.yhishi.github_user.R
import com.yhishi.github_user.databinding.ItemUserBinding
import com.yhishi.github_user.domain.model.ui.User


/**
 * ユーザー一覧のAdapter
 */
class UserAdapter : ListAdapter<User, UserViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean = areItemsTheSame(oldItem, newItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.apply {
            userName.text = data.userName
            Picasso.get()
                .load(data.avatarUrl)
                .into(avatarImage)
        }
    }
}
