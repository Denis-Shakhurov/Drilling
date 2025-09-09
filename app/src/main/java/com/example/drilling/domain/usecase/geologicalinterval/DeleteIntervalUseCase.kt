package com.example.drillingapp.domain.usecase.geologicalinterval

import com.example.drilling.domain.repository.GeologicalIntervalRepository
import javax.inject.Inject

class DeleteIntervalUseCase @Inject constructor(
    private val intervalRepository: GeologicalIntervalRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return try {
            intervalRepository.deleteInterval(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}