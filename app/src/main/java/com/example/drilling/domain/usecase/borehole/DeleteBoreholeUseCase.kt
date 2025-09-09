package com.example.drilling.domain.usecase.borehole

import androidx.compose.material3.pulltorefresh.PullToRefreshState
import com.example.drilling.domain.repository.BoreholeRepository
import javax.inject.Inject

class DeleteBoreholeUseCase @Inject constructor(
    private val boreholeRepository: BoreholeRepository
) {
    suspend operator fun invoke(id: Long): Result<Unit> {
        return try {
            boreholeRepository.deleteBorehole(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}