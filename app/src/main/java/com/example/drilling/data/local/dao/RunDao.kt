package com.example.drilling.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.drilling.data.local.entity.RunEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RunDao {
    @Query("SELECT * FROM runs WHERE holeId = :holeId ORDER BY startInterval")
    fun getRunsByHoleId(holeId: Long): Flow<List<RunEntity>>

    @Query("SELECT * FROM runs WHERE id = :id")
    suspend fun getRunById(id: Long): RunEntity?

    @Query("SELECT MAX(endInterval) FROM runs WHERE holeId = :holeId")
    suspend fun getMaxEndInterval(holeId: Long): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: RunEntity): Long

    @Update
    suspend fun updateRun(run: RunEntity)

    @Delete
    suspend fun deleteRun(run: RunEntity)

    @Query("DELETE FROM runs WHERE holeId = :holeId")
    suspend fun deleteRunsByHoleId(holeId: Long)
}