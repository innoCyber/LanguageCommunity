package com.innocent.languagecommunity.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.innocent.languagecommunity.data.model.CommunityUserInfo
import com.innocent.languagecommunity.data.remote.FetchCommunityDataService

class CommunityPagingSource(private val communityDataService: FetchCommunityDataService): PagingSource<Int,CommunityUserInfo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CommunityUserInfo> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try{
            val response = communityDataService.getCommunityUsersInfo(page, params.loadSize)
            LoadResult.Page(data = response.result,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.result.isEmpty()) null else page.plus(1))
        }catch (exception: Exception){

            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CommunityUserInfo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}