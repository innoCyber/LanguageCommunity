package com.innocent.languagecommunity.util.PreferencesDataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesDataStoreImpl @Inject constructor(@ApplicationContext context: Context): PreferencesDataStore {

    private val dataStore = context.createDataStore(name = "button_state_prefs")

    companion object {
        val IS_COMMUNITY_CARD_CLICKED = preferencesKey<Boolean>("IS_COMMUNITY_CARD_CLICKED")
        val IS_LIKE_BUTTON_CLICKED = preferencesKey<Boolean>("IS_LIKE_BUTTON_CLICKED")
    }

    override suspend fun setButtonState(cardClicked: Boolean, likeButtonClicked: Boolean) {
            dataStore.edit {
                it[IS_COMMUNITY_CARD_CLICKED] = cardClicked
                it[IS_LIKE_BUTTON_CLICKED] = likeButtonClicked
            }
    }

    override suspend fun setCardClicked(cardClicked: Boolean) {
            dataStore.edit {
                it[IS_COMMUNITY_CARD_CLICKED] = cardClicked
            }
    }

    override val cardClicked: Flow<Boolean>
        get() = dataStore.data.map {
            it[IS_COMMUNITY_CARD_CLICKED] ?: false
        }
    override val likeButtonClicked: Flow<Boolean>
        get() = dataStore.data.map {
            it[IS_LIKE_BUTTON_CLICKED] ?: false
        }

}