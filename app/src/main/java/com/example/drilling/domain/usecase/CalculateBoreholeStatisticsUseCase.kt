package com.example.drillingapp.domain.usecase

import com.example.drilling.domain.repository.TripRepository
import javax.inject.Inject

class CalculateBoreholeStatisticsUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    suspend operator fun invoke(boreholeId: Long): BoreholeStatistics {
        val totalCoreLength = tripRepository.getTotalCoreLength(boreholeId)
        val maxDepth = tripRepository.getMaxDepth(boreholeId)

        val coreRecoveryPercentage = if (maxDepth > 0) {
            (totalCoreLength / maxDepth) * 100
        } else {
            0.0
        }

        return BoreholeStatistics(
            totalCoreLength = totalCoreLength,
            maxDepth = maxDepth,
            coreRecoveryPercentage = coreRecoveryPercentage
        )
    }
}

data class BoreholeStatistics(
    val totalCoreLength: Double,
    val maxDepth: Double,
    val coreRecoveryPercentage: Double
)