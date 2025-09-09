package com.example.drillingapp.domain.usecase.trip

import com.example.drilling.domain.repository.TripRepository
import javax.inject.Inject

class GetTotalCoreLengthUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    suspend operator fun invoke(boreholeId: Long): Double {
        return tripRepository.getTotalCoreLength(boreholeId)
    }
}