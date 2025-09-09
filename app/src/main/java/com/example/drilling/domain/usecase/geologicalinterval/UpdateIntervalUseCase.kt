package com.example.drillingapp.domain.usecase.geologicalinterval

import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.repository.GeologicalIntervalRepository
import javax.inject.Inject

class UpdateIntervalUseCase @Inject constructor(
    private val intervalRepository: GeologicalIntervalRepository
) {
    suspend operator fun invoke(interval: GeologicalInterval): Result<Unit> {
        return try {
            intervalRepository.updateInterval(interval)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}