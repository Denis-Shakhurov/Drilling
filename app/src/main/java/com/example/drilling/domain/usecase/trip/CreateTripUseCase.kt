package com.example.drillingapp.domain.usecase.trip

import android.util.Log
import com.example.drilling.domain.model.Trip
import com.example.drilling.domain.repository.TripRepository
import javax.inject.Inject

class CreateTripUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    suspend operator fun invoke(trip: Trip): Result<Long> {
        return try {
            val id = tripRepository.insertTrip(trip)
            Log.d("CreateTripUseCase", "Trip created with id: ${id}")
            Result.success(id)
        } catch (e: Exception) {
            Log.d("CreateTripUseCase", "Error: ${e.message}")
            Result.failure(e)
        }
    }
}