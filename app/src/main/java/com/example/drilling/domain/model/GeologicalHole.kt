package com.example.drilling.domain.model

data class GeologicalHole(
    val id: Long = 0,
    val name: String,
    val drillingRigNumber: String,
    val isClosed: Boolean = false,
    val closingDepth: Double = 0.0,
    val geologicalWorkDone: Boolean = false
)
