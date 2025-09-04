package com.example.drilling.data.datasource

import com.example.drilling.data.local.entity.GeologicalHoleEntity
import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import com.example.drilling.data.local.entity.RunEntity
import com.example.drilling.domain.model.GeologicalHole
import kotlinx.coroutines.flow.Flow

interface HoleLocalDataSource {
    fun getAllHoles(): Flow<List<GeologicalHoleEntity>>
    suspend fun getHoleById(id: Long): GeologicalHoleEntity?
    suspend fun insertHole(hole: GeologicalHoleEntity): Long
    suspend fun updateHole(hole: GeologicalHoleEntity)
    suspend fun deleteHole(hole: GeologicalHoleEntity)
    suspend fun deleteAllHoles()

    fun getRunsByHoleId(holeId: Long): Flow<List<RunEntity>>
    suspend fun getRunById(id: Long): RunEntity?
    suspend fun getMaxRunEndInterval(holeId: Long): Double?
    suspend fun insertRun(run: RunEntity): Long
    suspend fun updateRun(run: RunEntity)
    suspend fun deleteRun(run: RunEntity)
    suspend fun deleteRunSByHoleId(holeId: Long)

    fun getIntervalsByHoleId(holeId: Long): Flow<List<GeologicalIntervalEntity>>
    suspend fun getIntervalById(id: Long): GeologicalIntervalEntity?
    suspend fun getMaxIntervalEndInterval(holeId: Long): Double?
    suspend fun insertInterval(interval: GeologicalIntervalEntity): Long
    suspend fun updateInterval(interval: GeologicalIntervalEntity)
    suspend fun deleteInterval(interval: GeologicalIntervalEntity)
    suspend fun deleteIntervalsByHoleId(holeId: Long)
}