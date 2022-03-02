package com.innocent.languagecommunity.ui.helpers

import com.innocent.languagecommunity.data.model.CommunityUserInfo
import java.util.*

data class UserItemUiState(private val communityUserInfo: CommunityUserInfo) : BaseUiState() {

    fun getImageUrl() = communityUserInfo.pictureUrl

    fun getName() = communityUserInfo.firstName

    fun getID() = communityUserInfo.id.toString()

    fun getLearns() = communityUserInfo.learns[0].uppercase(Locale.ROOT)

    fun getNatives() = communityUserInfo.natives[0].uppercase(Locale.ROOT)

    fun getReferenceCnt() = communityUserInfo.referenceCnt

    fun getReferenceCntForNew() = communityUserInfo.referenceCnt

    fun getTopic() = communityUserInfo.topic

}