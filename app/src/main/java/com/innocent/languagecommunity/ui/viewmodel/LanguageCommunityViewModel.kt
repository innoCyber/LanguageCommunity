package com.innocent.languagecommunity.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.innocent.languagecommunity.data.repository.CommunityInfoRepository
import com.innocent.languagecommunity.ui.helpers.UserItemUiState
import com.innocent.languagecommunity.util.PreferencesDataStore.PreferencesDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageCommunityViewModel @Inject constructor(communityInfoRepository: CommunityInfoRepository, private val  preferencesDataStore: PreferencesDataStore) : ViewModel() {

    val communityUsersInfo = communityInfoRepository.getCommunityUsersInfo()
    val userItemsUiStates = communityUsersInfo.map { pagingData ->
            pagingData.map { communityUserInfo -> UserItemUiState(communityUserInfo) }
        }.cachedIn(viewModelScope)

    fun setButtonState(cardClicked: Boolean, likeButtonClicked: Boolean){
        viewModelScope.launch { preferencesDataStore.setButtonState(cardClicked,likeButtonClicked)}
    }

    val isCardClicked  = preferencesDataStore.cardClicked
    val isLikeButtonClicked  = preferencesDataStore.likeButtonClicked

}
