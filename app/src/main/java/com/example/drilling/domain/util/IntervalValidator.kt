package com.example.drilling.domain.util

import kotlin.collections.none

/**
 * Утилита для проверки пересечения интервалов
 */
object IntervalValidator {
    /**
     * Проверяет, пересекаются ли два интервала
     */
    fun intervalsOverlap(
        start1: Double,
        start2: Double,
        end1: Double,
        end2: Double
    ): Boolean {
        return start1 < end2 && end1 > start2
    }

    /**
     * Проверяет, что новый интервал не пересекается с существующими
     */
    fun <T> isIntervalValid(
        newStart: Double,
        newEnd: Double,
        existingIntervals: List<Pair<Double, Double>>,
        excludeId: Long? = null
    ): Boolean {
        return existingIntervals.none { (existingStart, existingEnd) ->
            intervalsOverlap(newStart, newEnd, existingStart, existingEnd)
        }
    }

    /**
     * Проверяет, что интервал полностью находится в пределах скважины
     */
    fun isWithinBorehole(
        intervalStart: Double,
        intervalEnd: Double,
        boreholeTotalDepth: Double
    ): Boolean {
        return intervalStart >= 0 && intervalEnd <= boreholeTotalDepth
    }
}