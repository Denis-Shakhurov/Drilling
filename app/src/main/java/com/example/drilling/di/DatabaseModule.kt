package com.example.drilling.di

import android.content.Context
import androidx.room.Room
import com.example.drilling.data.local.AppDatabase
import com.example.drilling.data.local.dao.GeologicalHoleDao
import com.example.drilling.data.local.dao.GeologicalIntervalDao
import com.example.drilling.data.local.dao.RunDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "drilling_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGeologicalHoleDao(database: AppDatabase): GeologicalHoleDao {
        return database.geologicalHoleDao()
    }

    @Provides
    @Singleton
    fun provideRunDao(database: AppDatabase): RunDao {
        return database.runDao()
    }

    @Provides
    @Singleton
    fun provideGeologicalIntervalDao(database: AppDatabase): GeologicalIntervalDao {
        return database.geologicalIntervalDao()
    }
}