package com.example.drilling.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geological_holes")
data class GeologicalHoleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val drillingRigNumber: String,
    val isClosed: Boolean = false,
    val closingDepth: Double = 0.0,
    val geologicalWorkDone: Boolean = false
)
