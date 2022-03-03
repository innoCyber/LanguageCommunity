package com.innocent.languagecommunity.api

import com.innocent.languagecommunity.data.model.CommunityAPIResponse
import com.innocent.languagecommunity.data.model.CommunityUserInfo
import com.innocent.languagecommunity.data.paging.CommunityPagingSource
import com.innocent.languagecommunity.data.remote.FetchCommunityDataService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class APITest{

    @Mock
    lateinit var api: FetchCommunityDataService
    lateinit var communityPagingSource: CommunityPagingSource

    companion object {

        private lateinit var service: FetchCommunityDataService

        val communityAPIResponse = CommunityAPIResponse(
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
    fun `test that API call returns a value`() = runBlockingTest {
        assertThat(communityAPIResponse, `is`(notNullValue()))
    }

    @Test
    fun `test that API call returns fetches members`() = runBlockingTest {
        val result = communityAPIResponse.result
        assertThat(result[0].firstName, `is`("Innocent"))
        assertThat(result[0].id, `is`(1))
        assertThat(result[0].learns, `is`(listOf("EN")))
        assertThat(result[0].natives, `is`(listOf("DE")))
        assertThat(result[0].pictureUrl, `is`("innocent.com"))
        assertThat(result[0].referenceCnt, `is`(1))
        assertThat(result[0].topic, `is`("Innocent"))
    }
}