package com.example.drilling.domain.usecase.borehole

import com.example.drilling.domain.model.Borehole
import com.example.drilling.domain.repository.BoreholeRepository
import javax.inject.Inject

class UpdateBoreholeUseCase @Inject constructor(
    private val boreholeRepository: BoreholeRepository
) {
    suspend operator fun invoke(borehole: Borehole): Result<Unit> {
        return try {
            boreholeRepository.updateBorehole(borehole)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}