package com.example.drilling.data.repository

import com.example.drilling.data.local.dao.GeologicalIntervalDao
import com.example.drilling.data.mapper.GeologicalIntervalMapper
import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.repository.GeologicalIntervalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeologicalIntervalRepositoryImpl @Inject constructor(
    private val intervalDao: GeologicalIntervalDao,
) : GeologicalIntervalRepository {

    override fun getIntervalsByBoreholeId(boreholeId: Long): Flow<List<GeologicalInterval>> {
        return intervalDao.getIntervalsByBoreholeId(boreholeId).map { entities ->
            GeologicalIntervalMapper.toDomainList(entities)
        }
    }

    override suspend fun getIntervalById(id: Long): GeologicalInterval?  {
        return intervalDao.getIntervalById(id)?.let { entity ->
            GeologicalIntervalMapper.toDomain(entity)
        }
    }

    override suspend fun insertInterval(interval: GeologicalInterval): Long {
        if (!interval.validate()) {
            throw IllegalArgumentException("Неккоректные данные геологического интервала")
        }

        //Проверка пересечения интервалов
        val overlappingIntervals = intervalDao.findOverlappingIntervals(
            interval.boreholeId,
            interval.startInterval,
            interval.endInterval
        )

        if (overlappingIntervals.isNotEmpty()) {
            throw IllegalArgumentException("Геологический интервал пересекается с существеюшими интервалами")
        }

        return intervalDao.insertInterval(GeologicalIntervalMapper.toEntity(interval))
    }

    override suspend fun updateInterval(interval: GeologicalInterval) {
        if (!interval.validate()) {
            throw IllegalArgumentException("Некорректные данные геологического интервала")
        }

        // Проверка пересечения интервалов (исключая текущий интервал)
        val overlappingIntervals = intervalDao.findOverlappingIntervals(
            interval.boreholeId,
            interval.startInterval,
            interval.endInterval,
            interval.id
        )

        if (overlappingIntervals.isNotEmpty()) {
            throw IllegalArgumentException("Геологический интервал пересекается с существующими интервалами")
        }

        intervalDao.updateInterval(GeologicalIntervalMapper.toEntity(interval))
    }

    override suspend fun deleteInterval(id: Long) {
        intervalDao.deleteIntervalById(id)
    }

    override suspend fun deleteIntervalsByBoreholeId(boreholeId: Long) {
        intervalDao.deleteIntervalsByBoreholeId(boreholeId)
    }

    override suspend fun getIntervalRangesByBoreholeId(boreholeId: Long): List<Pair<Double, Double>> {
        return intervalDao.getIntervalRangesByBoreholeId(boreholeId).map { Pair(it.startInterval, it.endInterval) }
    }

    override suspend fun getAvailableLithologies(): List<String> {
        return intervalDao.getAvailableLithologies()
    }

    override suspend fun getAvailableStratigraphies(): List<String> {
        return intervalDao.getAvailableStratigraphies()
    }
}