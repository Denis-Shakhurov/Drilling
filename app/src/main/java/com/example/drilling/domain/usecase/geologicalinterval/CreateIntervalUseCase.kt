package com.example.drillingapp.domain.usecase.geologicalinterval

import android.util.Log
import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.repository.GeologicalIntervalRepository
import javax.inject.Inject

class CreateIntervalUseCase @Inject constructor(
    private val intervalRepository: GeologicalIntervalRepository
) {
    suspend operator fun invoke(interval: GeologicalInterval): Result<Long> {
        return try {
            val id = intervalRepository.insertInterval(interval)
            Log.d("CreateIntervalUseCase", "Interval created with id: ${id}")
            Result.success(id)
        } catch (e: Exception) {
            Log.d("CreateIntervalUseCase", "Error: ${e.message}")
            Result.failure(e)
        }
    }
}