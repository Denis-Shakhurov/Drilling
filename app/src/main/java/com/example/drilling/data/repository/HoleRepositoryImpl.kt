package com.example.drilling.data.repository

import com.example.drilling.data.datasource.HoleLocalDataSource
import com.example.drilling.data.mapper.EntityToDomainMapper
import com.example.drilling.domain.model.GeologicalHole
import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.model.Run
import com.example.drilling.domain.repository.HoleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HoleRepositoryImpl @Inject constructor(
    private val localDataSource: HoleLocalDataSource
) : HoleRepository {
    override fun getAllHoles(): Flow<List<GeologicalHole>> =
        localDataSource.getAllHoles().map { entities ->
            entities.map { EntityToDomainMapper.toDomain(it) }
    }

    override suspend fun getHoleById(id: Long): GeologicalHole? {
        return localDataSource.getHoleById(id)?.let { EntityToDomainMapper.toDomain(it) }
    }

    override suspend fun insertHole(hole: GeologicalHole): Long {
        val entity = EntityToDomainMapper.toEntity(hole)
        return localDataSource.insertHole(entity)
    }

    override suspend fun updateHole(hole: GeologicalHole) {
        val entity = EntityToDomainMapper.toEntity(hole)
        localDataSource.updateHole(entity)
    }

    override suspend fun deleteHole(hole: GeologicalHole) {
        val entity = EntityToDomainMapper.toEntity(hole)
        localDataSource.deleteHole(entity)
    }

    override suspend fun deleteAllHoles() {
        localDataSource.deleteAllHoles()
    }

    override fun getRunsByHoleId(holeId: Long): Flow<List<Run>> =
        localDataSource.getRunsByHoleId(holeId).map { entities ->
            entities.map { EntityToDomainMapper.toDomain(it) }
        }

    override suspend fun getRunById(id: Long): Run? {
        return localDataSource.getRunById(id)?.let { EntityToDomainMapper.toDomain(it) }
    }

    override suspend fun getMaxRunEndInterval(holeId: Long): Double? {
        return localDataSource.getMaxRunEndInterval(holeId)
    }

    override suspend fun insertRun(run: Run): Long {
        val entity = EntityToDomainMapper.toEntity(run)
        return localDataSource.insertRun(entity)
    }

    override suspend fun updateRun(run: Run) {
        val entity = EntityToDomainMapper.toEntity(run)
        localDataSource.updateRun(entity)
    }

    override suspend fun deleteRun(run: Run) {
        val entity = EntityToDomainMapper.toEntity(run)
        localDataSource.deleteRun(entity)
    }

    override suspend fun deleteRunsByHoleId(holeId: Long) {
        localDataSource.deleteRunSByHoleId(holeId)
    }

    override suspend fun hasRunIntervalOverlap(
        holeId: Long,
        startInterval: Double,
        endInterval: Double,
        excludeRunId: Long?
    ): Boolean {
        val runs = getRunsByHoleId(holeId).firstOrNull() ?: return false
        val runsToCheck = if (excludeRunId != null) {
            runs.filter { it.id != excludeRunId }
        } else {
            runs
        }

        return runsToCheck.any { run ->
            startInterval < run.endInterval && endInterval > run.startInterval
        }
    }

    override fun getIntervalsByHoleId(holeId: Long): Flow<List<GeologicalInterval>> =
        localDataSource.getIntervalsByHoleId(holeId).map { entities ->
            entities.map { EntityToDomainMapper.toDomain(it) }
        }

    override suspend fun getIntervalById(id: Long): GeologicalInterval? {
        return localDataSource.getIntervalById(id)?.let { EntityToDomainMapper.toDomain(it) }
    }

    override suspend fun getMaxIntervalEndInterval(holeId: Long): Double? {
        return localDataSource.getMaxIntervalEndInterval(holeId)
    }

    override suspend fun insertInterval(interval: GeologicalInterval): Long {
        val entity = EntityToDomainMapper.toEntity(interval)
        return localDataSource.insertInterval(entity)
    }

    override suspend fun updateInterval(interval: GeologicalInterval) {
        val entity = EntityToDomainMapper.toEntity(interval)
        localDataSource.updateInterval(entity)
    }

    override suspend fun deleteInterval(interval: GeologicalInterval) {
        val entity = EntityToDomainMapper.toEntity(interval)
        localDataSource.deleteInterval(entity)
    }

    override suspend fun deleteIntervalsByHoleId(holeId: Long) {
        localDataSource.deleteIntervalsByHoleId(holeId)
    }

    override suspend fun hasIntervalOverlap(
        holeId: Long,
        startInterval: Double,
        endInterval: Double,
        excludeIntervalId: Long?
    ): Boolean {
        val intervals = getIntervalsByHoleId(holeId).firstOrNull() ?: return false
        val intervalsToCheck = if (excludeIntervalId != null) {
            intervals.filter { it.id != excludeIntervalId }
        } else {
            intervals
        }

        return intervalsToCheck.any { interval ->
            startInterval < interval.endInterval && endInterval > interval.startInterval
        }
    }
}