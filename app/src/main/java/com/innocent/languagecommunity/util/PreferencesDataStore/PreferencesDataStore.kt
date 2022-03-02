package com.innocent.languagecommunity.util.PreferencesDataStore

import kotlinx.coroutines.flow.Flow

interface PreferencesDataStore {

    suspend fun setButtonState(cardClicked: Boolean, likeButtonClicked: Boolean)
    suspend fun setCardClicked(cardClicked: Boolean)
    val cardClicked: Flow<Boolean>
    val likeButtonClicked: Flow<Boolean>
}