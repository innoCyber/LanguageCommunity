package com.innocent.languagecommunity.ui.helpers

import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.innocent.languagecommunity.databinding.ItemPagingFooterBinding
import com.innocent.languagecommunity.util.executeWithAction

class FooterViewHolder (
    private val binding: ItemPagingFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.btnRetry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.executeWithAction {
            footerUiState = FooterUiState(loadState)
        }
    }
}

