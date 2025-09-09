package com.example.drillingapp.domain.usecase.trip

import com.example.drilling.domain.model.Trip
import com.example.drilling.domain.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTripsByBoreholeIdUseCase @Inject constructor(
    private val tripRepository: TripRepository
) {
    operator fun invoke(boreholeId: Long): Flow<List<Trip>> {
        return tripRepository.getTripsByBoreholeId(boreholeId)
    }
}