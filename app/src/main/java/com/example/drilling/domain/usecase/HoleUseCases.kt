package com.example.drilling.domain.usecase

import com.example.drilling.domain.model.GeologicalHole
import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.model.Run
import com.example.drilling.domain.repository.HoleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHolesUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    operator fun invoke(): Flow<List<GeologicalHole>> = repository.getAllHoles()
}

class GetHoleByIdUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(id: Long): GeologicalHole? = repository.getHoleById(id)
}

class InsertHoleUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(hole: GeologicalHole): Long = repository.insertHole(hole)
}

class UpdateHoleUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(hole: GeologicalHole) = repository.updateHole(hole)
}

class DeleteHoleUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(hole: GeologicalHole) = repository.deleteHole(hole)
}

class DeleteAllHoleUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke() = repository.deleteAllHoles()
}

//Run Use Cases
class GetRunsByHoleIdUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    operator fun invoke(holeId: Long): Flow<List<Run>> = repository.getRunsByHoleId(holeId)
}

class GetRunByIdUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(id: Long): Run? = repository.getRunById(id)
}

class GetMaxRunEndIntervalUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(holeId: Long): Double? = repository.getMaxRunEndInterval(holeId)
}

class InsertRunUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(run: Run): Long = repository.insertRun(run)
}

class UpdateRunUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(run: Run) = repository.updateRun(run)
}

class DeleteRunUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(run: Run) = repository.deleteRun(run)
}

class DeleteRunsByHoleIdUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(holeId: Long) = repository.deleteRunsByHoleId(holeId)
}

class HasRunIntervalOverlapUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(
        holeId: Long,
        startInterval: Double,
        endInterval: Double,
        excludeRunId: Long? = null
    ): Boolean = repository.hasRunIntervalOverlap(holeId, startInterval, endInterval, excludeRunId)
}

// Interval Use Case
class GetIntervalsByHoleIdUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    operator fun invoke(holeId: Long): Flow<List<GeologicalInterval>> =
        repository.getIntervalsByHoleId(holeId)
}

class GetIntervalByIdUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(id: Long): GeologicalInterval? = repository.getIntervalById(id)
}

class GetMaxIntervalEndIntervalUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(holeId: Long): Double? = repository.getMaxIntervalEndInterval(holeId)
}

class InsertIntervalUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(interval: GeologicalInterval): Long = repository.insertInterval(interval)
}

class UpdateIntervalUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(interval: GeologicalInterval) = repository.updateInterval(interval)
}

class DeleteIntervalUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(interval: GeologicalInterval) = repository.deleteInterval(interval)
}

class DeleteIntervalsByHoleIdUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(holeId: Long) = repository.deleteIntervalsByHoleId(holeId)
}

class HasIntervalOverlapUseCase @Inject constructor(
    private val repository: HoleRepository
) {
    suspend operator fun invoke(
        holeId: Long,
        startInterval: Double,
        endInterval: Double,
        excludeIntervalId: Long? = null
    ): Boolean = repository.hasIntervalOverlap(holeId, startInterval, endInterval, excludeIntervalId)
}