package com.innocent.languagecommunity.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import com.innocent.languagecommunity.R
import com.innocent.languagecommunity.databinding.ActivityMainBinding
import com.innocent.languagecommunity.ui.adapter.LanguageCommunityAdapter
import com.innocent.languagecommunity.ui.helpers.FooterAdapter
import com.innocent.languagecommunity.ui.helpers.UserItemUiState
import com.innocent.languagecommunity.ui.helpers.UsersUiState
import com.innocent.languagecommunity.ui.viewmodel.LanguageCommunityViewModel
import com.innocent.languagecommunity.util.collect
import com.innocent.languagecommunity.util.collectLast
import com.innocent.languagecommunity.util.executeWithAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: LanguageCommunityViewModel by viewModels()

    private var isCardClicked: Boolean = false
    private var isLikeButtonClicked: Boolean = false

    @Inject
    lateinit var userAdapter: LanguageCommunityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setAdapter()
        setListener()
        collectLast(viewModel.userItemsUiStates, ::setUsers)
        setUpObservers()
    }

    private fun setListener() {
        binding.btnRetry.setOnClickListener { userAdapter.retry() }
    }


    private fun setAdapter() {
        collect(flow = userAdapter.loadStateFlow
            .distinctUntilChangedBy { it.source.refresh }
            .map { it.refresh },
            action = ::setUsersUiState
        )
        addRecyclerviewDividers()
        binding.learningCommunityUsersRecyclerview.adapter = userAdapter.withLoadStateFooter(FooterAdapter(userAdapter::retry))
    }

    private fun setUsersUiState(loadState: LoadState) {
        binding.executeWithAction {
            usersUiState = UsersUiState(loadState)
        }
    }

    private suspend fun setUsers(userItemsPagingData: PagingData<UserItemUiState>) {
        userAdapter.submitData(userItemsPagingData)
    }

    private fun addRecyclerviewDividers(){
        binding.learningCommunityUsersRecyclerview.addItemDecoration( DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    private fun setUpObservers(){
        viewModel.isLikeButtonClicked.asLiveData().observe(this){
            isLikeButtonClicked = it
        }
        viewModel.isCardClicked.asLiveData().observe(this){
            isCardClicked = it
        }
    }
}