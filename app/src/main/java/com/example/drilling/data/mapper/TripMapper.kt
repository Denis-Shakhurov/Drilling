package com.example.drilling.data.mapper

import com.example.drilling.data.local.entity.TripEntity
import com.example.drilling.domain.model.Trip

/**
 * Маппер для преобразования между domain моделью Trip и entity TripEntity
 */
object TripMapper {
    /**
     * Преобразует domain модель в entity
     */
    fun toEntity(domain: Trip): TripEntity {
        return TripEntity(
            id = domain.id,
            boreholeId = domain.boreholeId,
            startInterval = domain.startInterval,
            endInterval = domain.endInterval,
            coreLength = domain.coreLength
        )
    }

    /**
     * Преобразует entity в domain модель
     */
    fun toDomain(entity: TripEntity): Trip {
        return Trip(
            id = entity.id,
            boreholeId = entity.boreholeId,
            startInterval = entity.startInterval,
            endInterval = entity.endInterval,
            coreLength = entity.coreLength
        )
    }

    /**
     * Преобразует список entity в список domain моделей
     */
    fun toDomainList(entities: List<TripEntity>): List<Trip> {
        return entities.map { toDomain(it) }
    }
}