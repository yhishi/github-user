package com.yhishi.github_user.view.userDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yhishi.github_user.R
import com.yhishi.github_user.databinding.ItemRepositoryBinding
import com.yhishi.github_user.domain.model.ui.Repository


/**
 * リポジトリ一覧のAdapter
 */
class RepositoryAdapter(
    private val onClickItem: (String) -> Unit,
) : ListAdapter<Repository, RepositoryViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(
            oldItem: Repository,
            newItem: Repository
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Repository,
            newItem: Repository
        ): Boolean = areItemsTheSame(oldItem, newItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = DataBindingUtil.inflate<ItemRepositoryBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_repository,
            parent,
            false
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.repository = data
        holder.binding.root.setOnClickListener {
            onClickItem(data.repositoryUrl)
        }
    }
}
