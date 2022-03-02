package com.innocent.languagecommunity.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.innocent.languagecommunity.data.model.CommunityUserInfo
import com.innocent.languagecommunity.data.paging.CommunityPagingSource
import com.innocent.languagecommunity.data.remote.FetchCommunityDataService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommunityInfoRepositoryImpl @Inject constructor(private val communityDataService: FetchCommunityDataService): CommunityInfoRepository {
    override fun getCommunityUsersInfo(): Flow<PagingData<CommunityUserInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {CommunityPagingSource(communityDataService)}
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}