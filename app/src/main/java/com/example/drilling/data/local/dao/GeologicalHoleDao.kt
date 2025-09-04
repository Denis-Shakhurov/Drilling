package com.example.drilling.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.drilling.data.local.entity.GeologicalHoleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GeologicalHoleDao {
    @Query("SELECT * FROM geological_holes")
    fun getAllHoles(): Flow<List<GeologicalHoleEntity>>

    @Query("SELECT * FROM geological_holes WHERE id = :id")
    suspend fun getHoleById(id: Long): GeologicalHoleEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHole(hole: GeologicalHoleEntity): Long

    @Update
    suspend fun updateHole(hole: GeologicalHoleEntity)

    @Delete
    suspend fun deleteHole(hole: GeologicalHoleEntity)

    @Query("DELETE FROM geological_holes")
    suspend fun deleteAllHoles()
}