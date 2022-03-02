package com.innocent.languagecommunity.ui

import androidx.recyclerview.widget.RecyclerView
import com.innocent.languagecommunity.databinding.ItemUserBinding
import com.innocent.languagecommunity.ui.helpers.UserItemUiState
import com.innocent.languagecommunity.util.executeWithAction

class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(userItemUiState: UserItemUiState) {
        binding.executeWithAction {
            this.userItemUiState = userItemUiState
        }
    }
}