package com.yhishi.github_user.di

import com.yhishi.github_user.domain.repository.api.GithubService
import com.yhishi.github_user.domain.repository.api.impl.GithubServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Singleton
    @Binds
    fun bindsGithubService(impl: GithubServiceImpl): GithubService
}
