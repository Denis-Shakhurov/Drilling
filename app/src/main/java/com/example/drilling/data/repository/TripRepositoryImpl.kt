package com.example.drilling.data.repository

import com.example.drilling.data.local.dao.TripDao
import com.example.drilling.data.mapper.TripMapper
import com.example.drilling.domain.model.Trip
import com.example.drilling.domain.repository.TripRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TripRepositoryImpl @Inject constructor(
    private val tripDao: TripDao,
) : TripRepository {

    override fun getTripsByBoreholeId(boreholeId: Long): Flow<List<Trip>> {
        return tripDao.getTripsByBoreholeId(boreholeId).map { entities ->
            TripMapper.toDomainList(entities)
        }
    }

    override suspend fun getTripById(id: Long): Trip? =
        tripDao.getTripById(id)?.let { entity ->
            TripMapper.toDomain(entity)
        }

    override suspend fun insertTrip(trip: Trip): Long {
        if (!trip.validate()) {
            throw IllegalArgumentException("Неккоректные данные рейса")
        }

        //Проверка пересечения интервалов
        val overlappingTrips = tripDao.findOverlappingTrips(
            trip.boreholeId,
            trip.startInterval,
            trip.endInterval
        )

        if (overlappingTrips.isNotEmpty()) {
            throw IllegalArgumentException("Интервал рейса пересекается с существеющими рейсами ")
        }

        return tripDao.insertTrip(TripMapper.toEntity(trip))
    }

    override suspend fun updateTrip(trip: Trip) {
        if (!trip.validate()) {
            throw IllegalArgumentException("Неккоректные данные рейса")
        }

        //Проверка пересечения интервалов (исключая текущий рейс)
        val overlappingTrips = tripDao.findOverlappingTrips(
            trip.boreholeId,
            trip.startInterval,
            trip.endInterval,
            trip.id
        )

        if (overlappingTrips.isNotEmpty()) {
            throw IllegalArgumentException("Интервал рейса пересекается с существеющими рейсами")
        }

        tripDao.updateTrip(TripMapper.toEntity(trip))
    }

    override suspend fun deleteTrip(id: Long) {
        tripDao.deleteTripById(id)
    }

    override suspend fun deleteTripsByBoreholeId(boreholeId: Long) {
        tripDao.deleteTripsByBoreholeId(boreholeId)
    }

    override suspend fun getTripIntervalsByBoreholeId(boreholeId: Long): List<Pair<Double, Double>> {
        return tripDao.getTripIntervalsByBoreholeId(boreholeId)
            .map { Pair(it.startInterval, it.endInterval) }
    }

    override suspend fun getTotalCoreLength(boreholeId: Long): Double {
        return tripDao.getTotalCoreLength(boreholeId)
    }

    override suspend fun getMaxDepth(boreholeId: Long): Double {
        return tripDao.getMaxDepth(boreholeId)
    }
}
