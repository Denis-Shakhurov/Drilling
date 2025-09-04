package com.example.drilling.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geological_intervals")
data class GeologicalIntervalEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val holeId: Long,
    val startInterval: Double,
    val endInterval: Double,
    val lithology: String,
    val stratigraphy: String
)
