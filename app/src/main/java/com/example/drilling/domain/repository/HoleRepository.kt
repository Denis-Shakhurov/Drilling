package com.example.drilling.domain.repository

import com.example.drilling.domain.model.GeologicalHole
import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.model.Run
import kotlinx.coroutines.flow.Flow

interface HoleRepository {
    fun getAllHoles(): Flow<List<GeologicalHole>>
    suspend fun getHoleById(id: Long): GeologicalHole?
    suspend fun insertHole(hole: GeologicalHole): Long
    suspend fun updateHole(hole: GeologicalHole)
    suspend fun deleteHole(hole: GeologicalHole)
    suspend fun deleteAllHoles()

    fun getRunsByHoleId(holeId: Long): Flow<List<Run>>
    suspend fun getRunById(id: Long): Run?
    suspend fun getMaxRunEndInterval(holeId: Long): Double?
    suspend fun insertRun(run: Run): Long
    suspend fun updateRun(run: Run)
    suspend fun deleteRun(run: Run)
    suspend fun deleteRunsByHoleId(holeId: Long)
    suspend fun hasRunIntervalOverlap(holeId: Long,
                                      startInterval: Double,
                                      endInterval: Double,
                                      excludeRunId: Long?): Boolean

    fun getIntervalsByHoleId(holeId: Long): Flow<List<GeologicalInterval>>
    suspend fun getIntervalById(id: Long): GeologicalInterval?
    suspend fun getMaxIntervalEndInterval(holeId: Long): Double?
    suspend fun insertInterval(interval: GeologicalInterval): Long
    suspend fun updateInterval(interval: GeologicalInterval)
    suspend fun deleteInterval(interval: GeologicalInterval)
    suspend fun deleteIntervalsByHoleId(holeId: Long)
    suspend fun hasIntervalOverlap(holeId: Long,
                                   startInterval: Double,
                                   endInterval: Double,
                                   excludeIntervalId: Long?): Boolean
}