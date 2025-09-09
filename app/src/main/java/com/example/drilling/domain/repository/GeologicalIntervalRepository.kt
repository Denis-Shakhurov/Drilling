package com.example.drilling.domain.repository

import com.example.drilling.domain.model.GeologicalInterval
import kotlinx.coroutines.flow.Flow

interface GeologicalIntervalRepository {
    fun getIntervalsByBoreholeId(boreholeId: Long): Flow<List<GeologicalInterval>>
    suspend fun getIntervalById(id: Long): GeologicalInterval?
    suspend fun insertInterval(interval: GeologicalInterval): Long
    suspend fun updateInterval(interval: GeologicalInterval)
    suspend fun deleteInterval(id: Long)
    suspend fun deleteIntervalsByBoreholeId(boreholeId: Long)
    suspend fun getIntervalRangesByBoreholeId(boreholeId: Long): List<Pair<Double, Double>>
    suspend fun getAvailableLithologies(): List<String>
    suspend fun getAvailableStratigraphies(): List<String>
}