package com.example.drilling.data.mapper

import com.example.drilling.data.local.entity.GeologicalHoleEntity
import com.example.drilling.data.local.entity.GeologicalIntervalEntity
import com.example.drilling.data.local.entity.RunEntity
import com.example.drilling.domain.model.GeologicalHole
import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.model.Run

object EntityToDomainMapper {
    fun toDomain(entity: GeologicalHoleEntity): GeologicalHole {
        return GeologicalHole(
            id = entity.id,
            name = entity.name,
            drillingRigNumber = entity.drillingRigNumber,
            isClosed = entity.isClosed,
            closingDepth = entity.closingDepth,
            geologicalWorkDone = entity.geologicalWorkDone
        )
    }

    fun toDomain(entity: RunEntity): Run {
        return Run(
            id = entity.id,
            holeId = entity.holeId,
            startInterval = entity.startInterval,
            endInterval = entity.endInterval,
            penetration = entity.penetration
        )
    }

    fun toDomain(entity: GeologicalIntervalEntity): GeologicalInterval {
        return GeologicalInterval(
            id = entity.id,
            holeId = entity.holeId,
            startInterval = entity.startInterval,
            endInterval = entity.endInterval,
            lithology = entity.lithology,
            stratigraphy = entity.stratigraphy
        )
    }

    fun toEntity(domain: GeologicalHole): GeologicalHoleEntity {
        return GeologicalHoleEntity(
            id = domain.id,
            name = domain.name,
            drillingRigNumber = domain.drillingRigNumber,
            isClosed = domain.isClosed,
            closingDepth = domain.closingDepth,
            geologicalWorkDone = domain.geologicalWorkDone
        )
    }

    fun toEntity(domain: Run): RunEntity {
        return RunEntity(
            id = domain.id,
            holeId = domain.holeId,
            startInterval = domain.startInterval,
            endInterval = domain.endInterval,
            penetration = domain.penetration
        )
    }

    fun toEntity(domain: GeologicalInterval): GeologicalIntervalEntity {
        return GeologicalIntervalEntity(
            id = domain.id,
            holeId = domain.holeId,
            startInterval = domain.startInterval,
            endInterval = domain.endInterval,
            lithology = domain.lithology,
            stratigraphy = domain.stratigraphy
        )
    }
}