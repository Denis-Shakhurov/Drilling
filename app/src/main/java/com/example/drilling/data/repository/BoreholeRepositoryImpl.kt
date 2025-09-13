package com.example.drilling.data.repository

import com.example.drilling.data.local.dao.BoreholeDao
import com.example.drilling.data.mapper.BoreholeMapper
import com.example.drilling.domain.model.Borehole
import com.example.drilling.domain.repository.BoreholeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BoreholeRepositoryImpl @Inject constructor(
    private val boreholeDao: BoreholeDao,
): BoreholeRepository {

    override fun getAllBoreholes(): Flow<List<Borehole>> {
        return boreholeDao.getAllBoreholes().map { entities ->
            BoreholeMapper.toDomainList(entities)
        }
    }

    override suspend fun getBoreholeById(id: Long): Borehole? {
        return boreholeDao.getBoreholeById(id)?.let { entity ->
            BoreholeMapper.toDomain(entity)
        }
    }

    override suspend fun insertBorehole(borehole: Borehole): Long {

        if (!borehole.validate()) {
            throw IllegalArgumentException("Неккоректные данные скважины")
        }

        return boreholeDao.insertBorehole(BoreholeMapper.toEntity(borehole))
    }

    override suspend fun updateBorehole(borehole: Borehole) {

        if (!borehole.validate()) {
            throw IllegalArgumentException("Некорректные данные скважины")
        }

        boreholeDao.updateBorehole(BoreholeMapper.toEntity(borehole))
    }

    override suspend fun deleteBorehole(id: Long) {
        boreholeDao.deleteBoreholeById(id)
    }

    override suspend fun getBoreholesByDrillRing(drillRigNumber: Int): List<Borehole> {
        return boreholeDao.getBoreholeByDrillRig(drillRigNumber).let { entities ->
            BoreholeMapper.toDomainList(entities)
        }
    }
}