package com.example.drilling.domain.model

data class GeologicalInterval(
    val id: Long = 0,
    val holeId: Long,
    val startInterval: Double,
    val endInterval: Double,
    val lithology: String,
    val stratigraphy: String,
) {
    val intervalLength: Double
        get() = endInterval - startInterval
}
