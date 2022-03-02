package com.innocent.languagecommunity.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.innocent.languagecommunity.R
import com.innocent.languagecommunity.databinding.ItemUserBinding
import com.innocent.languagecommunity.ui.UserViewHolder
import com.innocent.languagecommunity.ui.helpers.UserItemUiState
import javax.inject.Inject

class LanguageCommunityAdapter @Inject constructor(): PagingDataAdapter<UserItemUiState, UserViewHolder>(Comparator){
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let{userItemUiState ->  holder.bind(userItemUiState)}
        holder.binding.recyclerItems.setOnClickListener {
            holder.binding.likeButton.visibility = View.INVISIBLE
            holder.binding.likeButtonClicked.visibility = View.VISIBLE
        }
        holder.binding.likeButton.setOnClickListener {
            holder.binding.likeButton.visibility = View.INVISIBLE
            holder.binding.likeButtonClicked.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    object Comparator : DiffUtil.ItemCallback<UserItemUiState>() {
        override fun areItemsTheSame(oldItem: UserItemUiState, newItem: UserItemUiState): Boolean {
            return oldItem.getID() == newItem.getID()
        }

        override fun areContentsTheSame(
            oldItem: UserItemUiState,
            newItem: UserItemUiState
        ): Boolean {
            return oldItem == newItem
        }
    }

}