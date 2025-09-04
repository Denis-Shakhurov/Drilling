package com.example.drilling.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GeologicalIntervalDao {
    @Query("SELECT * FROM geological_intervals WHERE holeId = :holeId ORDER BY startInterval")
    fun getIntervalsByHoleId(holeId: Long): Flow<List<GeologicalIntervalEntity>>

    @Query("SELECT * FROM geological_intervals WHERE id = :id")
    suspend fun getIntervalById(id: Long): GeologicalIntervalEntity?

    @Query("SELECT MAX(endInterval) FROM geological_intervals WHERE holeId = :holeId")
    suspend fun getMaxEndInterval(holeId: Long): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInterval(interval: GeologicalIntervalEntity): Long

    @Update
    suspend fun updateInterval(interval: GeologicalIntervalEntity)

    @Delete
    suspend fun deleteInterval(interval: GeologicalIntervalEntity)

    @Query("DELETE FROM geological_intervals WHERE holeId = :holeId")
    suspend fun deleteIntervalsByHoleId(holeId: Long)
}
