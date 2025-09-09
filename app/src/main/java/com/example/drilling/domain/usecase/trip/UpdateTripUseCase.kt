package com.example.drillingapp.domain.usecase.trip

import com.example.drilling.domain.model.Trip
import com.example.drilling.domain.repository.TripRepository
import javax.inject.Inject

class UpdateTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    suspend operator fun invoke(trip: Trip): Result<Unit> {
        return try {
            tripRepository.updateTrip(trip)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}