package com.example.drilling.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Entity класс для таблицы рейсов в базе данных
 */
@Entity(
    tableName = "trips",
    foreignKeys = [
        ForeignKey(
            entity = BoreholeEntity::class,
            parentColumns = ["id"],
            childColumns = ["boreholeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("boreholeId")]
)
data class TripEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val boreholeId: Long,
    val startInterval: Double,
    val endInterval: Double,
    val coreLength: Double
)
