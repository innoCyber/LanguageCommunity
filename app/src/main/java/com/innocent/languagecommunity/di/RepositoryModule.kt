package com.innocent.languagecommunity.di

import com.innocent.languagecommunity.data.repository.CommunityInfoRepository
import com.innocent.languagecommunity.data.repository.CommunityInfoRepositoryImpl
import com.innocent.languagecommunity.util.PreferencesDataStore.PreferencesDataStore
import com.innocent.languagecommunity.util.PreferencesDataStore.PreferencesDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCommunityInfoRepository(communityInfoRepoImpl: CommunityInfoRepositoryImpl): CommunityInfoRepository

    @Binds
    abstract fun providePreferencesDataStoreRepository(preferencesDataStoreImpl: PreferencesDataStoreImpl): PreferencesDataStore

}