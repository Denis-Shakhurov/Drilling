package com.example.drillingapp.domain.usecase

import com.example.drilling.domain.repository.GeologicalIntervalRepository
import com.example.drilling.domain.repository.TripRepository
import com.example.drilling.domain.util.IntervalValidator
import javax.inject.Inject

class ValidateIntervalUseCase @Inject constructor(
    private val tripRepository: TripRepository,
    private val intervalRepository: GeologicalIntervalRepository
) {
    suspend operator fun invoke(
        boreholeId: Long,
        start: Double,
        end: Double,
        excludeTripId: Long? = null,
        excludeIntervalId: Long? = null
    ): Boolean {
        // Получаем существующие интервалы
        val tripIntervals = tripRepository.getTripIntervalsByBoreholeId(boreholeId)
        val geologicalIntervals = intervalRepository.getIntervalRangesByBoreholeId(boreholeId)

        // Проверяем пересечение с рейсами
        val hasTripOverlap = tripIntervals.any { (existingStart, existingEnd) ->
            IntervalValidator.intervalsOverlap(start, end, existingStart, existingEnd)
        }

        // Проверяем пересечение с геологическими интервалами
        val hasIntervalOverlap = geologicalIntervals.any { (existingStart, existingEnd) ->
            IntervalValidator.intervalsOverlap(start, end, existingStart, existingEnd)
        }

        return !hasTripOverlap && !hasIntervalOverlap
    }
}