package com.example.drillingapp.domain.usecase.trip

import com.example.drilling.domain.model.Trip
import com.example.drilling.domain.repository.TripRepository
import javax.inject.Inject

class GetTripByIdUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    suspend operator fun invoke(id: Long): Trip? {
        return tripRepository.getTripById(id)
    }
}