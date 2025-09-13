package com.example.drilling.data.mapper

import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import com.example.drilling.domain.model.GeologicalInterval

/**
 * Маппер для преобразования между domain моделью GeologicalInterval и entity GeologicalIntervalEntity
 */
object GeologicalIntervalMapper {
    /**
     * Преобразует domain модель в entity
     */
    fun toEntity(domain: GeologicalInterval): GeologicalIntervalEntity {
        return GeologicalIntervalEntity(
            id = domain.id,
            boreholeId = domain.boreholeId,
            startInterval = domain.startInterval,
            endInterval = domain.endInterval,
            lithology = domain.lithology,
            stratigraphy = domain.stratigraphy
        )
    }

    /**
     * Преобразует entity в domain модель
     */
    fun toDomain(entity: GeologicalIntervalEntity): GeologicalInterval {
        return GeologicalInterval(
            id = entity.id,
            boreholeId = entity.boreholeId,
            startInterval = entity.startInterval,
            endInterval = entity.endInterval,
            lithology = entity.lithology,
            stratigraphy = entity.stratigraphy
        )
    }

    /**
     * Преобразует список entity в список domain моделей
     */
    fun toDomainList(entities: List<GeologicalIntervalEntity>): List<GeologicalInterval> {
        return entities.map { toDomain(it) }
    }
}