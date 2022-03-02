package com.innocent.languagecommunity.data.model

import com.google.gson.annotations.SerializedName

data class CommunityAPIResponse ( @SerializedName("response")val result: List<CommunityUserInfo>)