package com.example.drilling.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity класс для таблицы скважин в базе данных
 */
@Entity(tableName = "boreholes")
data class BoreholeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val isClosed: Boolean = false,
    val drillRigNumber: Int,
    val totalDepth: Double = 0.0,
    val closureDepth: Double? = null,
    val columnSet: Double? = null,
    val deathMeasurement: Double? = null,
    val workingMeasurement: Double? = null,
    val countHQ: Int? = null,
    val createdAt: Long
)
