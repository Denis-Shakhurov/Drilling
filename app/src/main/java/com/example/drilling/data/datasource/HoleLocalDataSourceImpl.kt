package com.example.drilling.data.datasource

import com.example.drilling.data.local.dao.GeologicalHoleDao
import com.example.drilling.data.local.dao.GeologicalIntervalDao
import com.example.drilling.data.local.dao.RunDao
import com.example.drilling.data.local.entity.GeologicalHoleEntity
import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import com.example.drilling.data.local.entity.RunEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HoleLocalDataSourceImpl @Inject constructor(
    private val holeDao: GeologicalHoleDao,
    private val runDao: RunDao,
    private val intervalDao: GeologicalIntervalDao
) : HoleLocalDataSource {
    override fun getAllHoles(): Flow<List<GeologicalHoleEntity>> = holeDao.getAllHoles()

    override suspend fun getHoleById(id: Long): GeologicalHoleEntity? = holeDao.getHoleById(id)

    override suspend fun insertHole(hole: GeologicalHoleEntity): Long = holeDao.insertHole(hole)

    override suspend fun updateHole(hole: GeologicalHoleEntity) = holeDao.updateHole(hole)

    override suspend fun deleteHole(hole: GeologicalHoleEntity) = holeDao.deleteHole(hole)

    override suspend fun deleteAllHoles() = holeDao.deleteAllHoles()

    override fun getRunsByHoleId(holeId: Long): Flow<List<RunEntity>> = runDao.getRunsByHoleId(holeId)

    override suspend fun getRunById(id: Long): RunEntity? = runDao.getRunById(id)

    override suspend fun getMaxRunEndInterval(holeId: Long): Double? = runDao.getMaxEndInterval(holeId)

    override suspend fun insertRun(run: RunEntity): Long = runDao.insertRun(run)

    override suspend fun updateRun(run: RunEntity) = runDao.updateRun(run)

    override suspend fun deleteRun(run: RunEntity) = runDao.deleteRun(run)

    override suspend fun deleteRunSByHoleId(holeId: Long) = runDao.deleteRunsByHoleId(holeId)

    override fun getIntervalsByHoleId(holeId: Long): Flow<List<GeologicalIntervalEntity>> = intervalDao.getIntervalsByHoleId(holeId)

    override suspend fun getIntervalById(id: Long): GeologicalIntervalEntity? = intervalDao.getIntervalById(id)

    override suspend fun getMaxIntervalEndInterval(holeId: Long): Double? = intervalDao.getMaxEndInterval(holeId)

    override suspend fun insertInterval(interval: GeologicalIntervalEntity) = intervalDao.insertInterval(interval)

    override suspend fun updateInterval(interval: GeologicalIntervalEntity) = intervalDao.updateInterval(interval)

    override suspend fun deleteInterval(interval: GeologicalIntervalEntity) = intervalDao.deleteInterval(interval)

    override suspend fun deleteIntervalsByHoleId(holeId: Long) = intervalDao.deleteIntervalsByHoleId(holeId)
}