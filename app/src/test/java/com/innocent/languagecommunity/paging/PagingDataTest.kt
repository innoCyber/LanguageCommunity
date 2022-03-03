package com.innocent.languagecommunity.paging

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.innocent.languagecommunity.data.model.CommunityAPIResponse
import com.innocent.languagecommunity.data.model.CommunityUserInfo
import com.innocent.languagecommunity.data.paging.CommunityPagingSource
import com.innocent.languagecommunity.data.remote.FetchCommunityDataService
import com.nhaarman.mockitokotlin2.given
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class PagingDataTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    lateinit var api: FetchCommunityDataService
    lateinit var communityPagingSource: CommunityPagingSource
    companion object {
        val communityAPIResponse = CommunityAPIResponse(
            result = listOf(
                CommunityUserInfo("Innocent",1, listOf("EN"), listOf("DE"),"innocent.com",1,"Innocent")
            )
        )

        val nextcommunityAPIResponse = CommunityAPIResponse(
            result = listOf(
                CommunityUserInfo("Innocent",1, listOf("EN"), listOf("DE"),"innocent.com",1,"Innocent")
            )
        )
    }
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        communityPagingSource = CommunityPagingSource(api)
    }

    @Test
    fun `community paging source load - failure - http error`() = runBlockingTest {
        val error = RuntimeException("404", Throwable())
        given(api.getCommunityUsersInfo(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).willThrow(error)
        val expectedResult = PagingSource.LoadResult.Error<Int, CommunityUserInfo>(error)
        assertEquals(
            expectedResult, communityPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `community paging source load - failure - received null`() = runBlockingTest {
        given(api.getCommunityUsersInfo(ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).willReturn(null)
        val expectedResult = PagingSource.LoadResult.Error<Int, CommunityUserInfo>(NullPointerException())
        assertEquals(
            expectedResult.toString(), communityPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ).toString()
        )
    }

}