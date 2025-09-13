package com.example.drilling.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.drilling.data.local.entity.TripEntity
import com.example.drillingapp.data.local.model.TripInterval
import kotlinx.coroutines.flow.Flow

@Dao
interface RunDao {
    @Query("SELECT * FROM trips WHERE boreholeId = :boreholeId ORDER BY startInterval ASC")
    fun getTripsByBoreholeId(boreholeId: Long): Flow<List<TripEntity>>

    @Query("SELECT * FROM trips WHERE id = :id")
    suspend fun getTripById(id: Long): TripEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertTrip(trip: TripEntity): Long

    @Update
    suspend fun updateTrip(trip: TripEntity)

    @Delete
    suspend fun deleteTrip(trip: TripEntity)

    @Query("DELETE FROM trips WHERE id = :id")
    suspend fun deleteTripById(id: Long)

    @Query("DELETE FROM trips WHERE boreholeId = :boreholeId")
    suspend fun deleteTripsByBoreholeId(boreholeId: Long)

    @Query("SELECT startInterval, endInterval FROM trips WHERE boreholeId = :boreholeId")
    suspend fun getTripIntervalsByBoreholeId(boreholeId: Long): List<TripInterval>

    @Query("SELECT COALESCE(MAX(endInterval), 0) FROM trips WHERE boreholeId = :boreholeId")
    suspend fun getMaxDepth(boreholeId: Long): Double

    @Query("SELECT COALESCE(SUM(coreLength), 0) FROM trips WHERE boreholeId = :boreholeId")
    suspend fun getTotalCoreLength(boreholeId: Long): Double

    @Transaction
    @Query("SELECT * FROM trips WHERE boreholeId = :boreholeId AND (:start < endInterval AND :end > startInterval) AND id != :excludeId")
    suspend fun findOverlappingTrips(
        boreholeId: Long,
        start: Double,
        end: Double,
        excludeId: Long = 0
    ): List<TripEntity>
}