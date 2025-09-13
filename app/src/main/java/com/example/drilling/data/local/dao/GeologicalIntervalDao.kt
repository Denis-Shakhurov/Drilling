package com.example.drilling.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import com.example.drillingapp.data.local.model.GeologicalIntervalRange
import kotlinx.coroutines.flow.Flow

@Dao
interface GeologicalIntervalDao {
    @Query("SELECT * FROM geological_intervals WHERE boreholeId = :boreholeId ORDER BY startInterval ASC")
    fun getIntervalsByBoreholeId(boreholeId: Long): Flow<List<GeologicalIntervalEntity>>

    @Query("SELECT * FROM geological_intervals WHERE id = :id")
    suspend fun getIntervalById(id: Long): GeologicalIntervalEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertInterval(interval: GeologicalIntervalEntity): Long

    @Update
    suspend fun updateInterval(interval: GeologicalIntervalEntity)

    @Query("DELETE FROM geological_intervals WHERE id = :id")
    suspend fun deleteIntervalById(id: Long)

    @Query("DELETE FROM geological_intervals WHERE boreholeId = :boreholeId")
    suspend fun deleteIntervalsByBoreholeId(boreholeId: Long)

    @Query("SELECT startInterval, endInterval FROM geological_intervals WHERE boreholeId = :boreholeId")
    suspend fun getIntervalRangesByBoreholeId(boreholeId: Long): List<GeologicalIntervalRange>

    @Query("SELECT DISTINCT lithology FROM geological_intervals ORDER BY lithology ASC")
    suspend fun getAvailableLithologies(): List<String>

    @Query("SELECT DISTINCT stratigraphy FROM geological_intervals ORDER BY stratigraphy ASC")
    suspend fun getAvailableStratigraphies(): List<String>

    @Transaction
    @Query("SELECT * FROM geological_intervals WHERE boreholeId = :boreholeId AND (:start < endInterval AND :end > startInterval) AND id != :excludeId")
    suspend fun findOverlappingIntervals(
        boreholeId: Long,
        start: Double,
        end: Double,
        excludeId: Long = 0
    ): List<GeologicalIntervalEntity>
}
