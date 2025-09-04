package com.example.drilling.di

import com.example.drilling.data.datasource.HoleLocalDataSource
import com.example.drilling.data.datasource.HoleLocalDataSourceImpl
import com.example.drilling.data.repository.HoleRepositoryImpl
import com.example.drilling.domain.repository.HoleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindHoleLocalDataSource(
        impl: HoleLocalDataSourceImpl
    ): HoleLocalDataSource

    @Binds
    @Singleton
    abstract fun bindHoleRepository(
        impl: HoleRepositoryImpl
    ): HoleRepository
}