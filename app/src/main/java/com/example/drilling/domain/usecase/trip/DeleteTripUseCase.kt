package com.example.drillingapp.domain.usecase.trip

import com.example.drilling.domain.repository.TripRepository
import javax.inject.Inject

class DeleteTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return try {
            tripRepository.deleteTrip(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}