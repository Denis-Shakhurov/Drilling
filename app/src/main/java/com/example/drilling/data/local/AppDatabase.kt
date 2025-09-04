package com.example.drilling.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.drilling.data.local.dao.GeologicalHoleDao
import com.example.drilling.data.local.dao.GeologicalIntervalDao
import com.example.drilling.data.local.dao.RunDao
import com.example.drilling.data.local.entity.GeologicalHoleEntity
import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import com.example.drilling.data.local.entity.RunEntity

@Database(
    entities = [
        GeologicalHoleEntity::class,
        GeologicalIntervalEntity::class,
        RunEntity::class
    ] ,
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun geologicalHoleDao() : GeologicalHoleDao
    abstract fun runDao() : RunDao
    abstract fun geologicalIntervalDao() : GeologicalIntervalDao
}