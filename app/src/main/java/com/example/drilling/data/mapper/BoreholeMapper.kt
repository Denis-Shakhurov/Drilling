package com.example.drilling.data.mapper

import com.example.drilling.data.local.converter.LocalDateTimeConverter
import com.example.drilling.data.local.entity.BoreholeEntity
import com.example.drilling.domain.model.Borehole
import java.time.LocalDateTime

/**
 * Маппер для преобразования между domain моделью Borehole и entity BoreholeEntity
 */
object BoreholeMapper {
    /**
     * Преобразует domain модель в entity
     */
    fun toEntity(domain: Borehole): BoreholeEntity {
        return BoreholeEntity(
            id = domain.id,
            name = domain.name,
            isClosed = domain.isClosed,
            drillRigNumber = domain.drillRigNumber,
            totalDepth = domain.totalDepth,
            closureDepth = domain.closureDepth,
            countHQ = domain.countHQ,
            columnSet = domain.columnSet,
            deathMeasurement = domain.deathMeasurement,
            workingMeasurement = domain.workingMeasurement,
            createdAt = LocalDateTimeConverter()
                .dateToTimestamp(domain.createdAt) ?: System.currentTimeMillis()
        )
    }

    /**
     * Преобразует entity в domain модель
     */
    fun toDomain(entity: BoreholeEntity): Borehole {
        return Borehole(
            id = entity.id,
            name = entity.name,
            isClosed = entity.isClosed,
            drillRigNumber = entity.drillRigNumber,
            totalDepth = entity.totalDepth,
            closureDepth = entity.closureDepth,
            countHQ = entity.countHQ,
            columnSet = entity.columnSet,
            deathMeasurement = entity.deathMeasurement,
            workingMeasurement = entity.workingMeasurement,
            createdAt = LocalDateTimeConverter()
                .fromTimestamp(entity.createdAt) ?: LocalDateTime.now()
        )
    }

    /**
     * Преобразует список entity в список domain моделей
     */
    fun toDomainList(entities: List<BoreholeEntity>): List<Borehole> {
        return entities.map { toDomain(it) }
    }
}