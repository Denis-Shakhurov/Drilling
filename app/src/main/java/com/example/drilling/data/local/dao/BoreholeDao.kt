package com.example.drilling.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.drilling.data.local.entity.BoreholeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BoreholeDao {

    @Query("SELECT * FROM boreholes ORDER BY name ASC")
    fun getAllBoreholes(): Flow<List<BoreholeEntity>>

    @Query("SELECT * FROM boreholes WHERE id = :id")
    suspend fun getBoreholeById(id: Long): BoreholeEntity?

    @Query("SELECT * FROM boreholes WHERE drillRigNumber = :drillRigNumber ORDER BY name ASC")
    suspend fun getBoreholeByDrillRig(drillRigNumber: Int): List<BoreholeEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertBorehole(boreHole: BoreholeEntity): Long

    @Update
    suspend fun updateBorehole(boreHole: BoreholeEntity)

    @Delete
    suspend fun deleteBoreHole(boreHole: BoreholeEntity)

    @Query("DELETE FROM boreholes WHERE id = :id")
    suspend fun deleteBoreholeById(id: Long)

    @Query("SELECT COUNT(*) FROM boreholes WHERE name = :name AND id = :excludeId")
    suspend fun countBoreholeWithName(name: String, excludeId: Long = 0): Int

    @Query("SELECT MAX(totalDepth) FROM boreholes WHERE id = :boreholeId")
    suspend fun getBoreholeTotalDepth(boreholeId: Long): Double?
}