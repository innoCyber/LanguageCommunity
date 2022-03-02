package com.innocent.languagecommunity.data.model

data class CommunityUserInfo(
    val firstName: String,
    val id: Int,
    val learns: List<String>,
    val natives: List<String>,
    val pictureUrl: String,
    val referenceCnt: Int,
    val topic: String
)