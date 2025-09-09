package com.example.drilling.domain.repository

import com.example.drilling.domain.model.Trip
import kotlinx.coroutines.flow.Flow

interface TripRepository {
    fun getTripsByBoreholeId(boreholeId: Long): Flow<List<Trip>>
    suspend fun getTripById(id: Long): Trip?
    suspend fun insertTrip(trip: Trip): Long
    suspend fun updateTrip(trip: Trip)
    suspend fun deleteTrip(id: Long)
    suspend fun deleteTripsByBoreholeId(boreholeId: Long)
    suspend fun getTripIntervalsByBoreholeId(boreholeId: Long): List<Pair<Double, Double>>
    suspend fun getTotalCoreLength(boreholeId: Long): Double
    suspend fun getMaxDepth(boreholeId: Long): Double
}