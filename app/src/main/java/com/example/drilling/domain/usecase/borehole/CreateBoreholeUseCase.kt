package com.example.drilling.domain.usecase.borehole

import com.example.drilling.domain.model.Borehole
import com.example.drilling.domain.repository.BoreholeRepository
import javax.inject.Inject

class CreateBoreholeUseCase @Inject constructor(
    private val boreholeRepository: BoreholeRepository
) {
    suspend operator fun invoke(borehole: Borehole): Result<Long> {
        return try {
            val id = boreholeRepository.insertBorehole(borehole)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}