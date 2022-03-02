package com.innocent.languagecommunity.data.repository

import androidx.paging.PagingData
import com.innocent.languagecommunity.data.model.CommunityUserInfo
import kotlinx.coroutines.flow.Flow

interface CommunityInfoRepository {
    fun getCommunityUsersInfo(): Flow<PagingData<CommunityUserInfo>>
}