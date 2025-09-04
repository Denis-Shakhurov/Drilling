package com.example.drilling.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runs")
data class RunEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val holeId: Long,
    val startInterval: Double,
    val endInterval: Double,
    val penetration: Double
)
