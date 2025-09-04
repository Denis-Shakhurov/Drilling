package com.example.drilling.domain.model

data class Run(
    val id: Long = 0,
    val holeId: Long,
    val startInterval: Double,
    val endInterval: Double,
    val penetration: Double
) {
    val coreRecoveryPercentage: Double
        get() = if ((endInterval - startInterval) == 0.0) 0.0
                else (penetration / (endInterval - startInterval)) * 100
}
