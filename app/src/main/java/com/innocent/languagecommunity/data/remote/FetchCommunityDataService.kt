package com.innocent.languagecommunity.data.remote

import com.innocent.languagecommunity.data.model.CommunityAPIResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FetchCommunityDataService {
    @GET("community_{pageNumber}.json")
    suspend fun getCommunityUsersInfo(@Path("pageNumber") pageNumber: Int, @Query("results") results: Int): CommunityAPIResponse

}