package com.example.drilling.domain.repository

import com.example.drilling.domain.model.Borehole
import kotlinx.coroutines.flow.Flow

interface BoreholeRepository {
    fun getAllBoreholes(): Flow<List<Borehole>>
    suspend fun getBoreholeById(id: Long): Borehole?
    suspend fun insertBorehole(borehole: Borehole): Long
    suspend fun updateBorehole(borehole: Borehole)
    suspend fun deleteBorehole(id: Long)
    suspend fun getBoreholesByDrillRing(drillRigNumber: String): List<Borehole>
}