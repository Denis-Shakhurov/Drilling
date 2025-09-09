package com.example.drilling.domain.usecase.borehole

import com.example.drilling.domain.model.Borehole
import com.example.drilling.domain.repository.BoreholeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBoreholesUseCase @Inject constructor(
    private val boreholeRepository: BoreholeRepository
) {
    suspend fun invoke(): Flow<List<Borehole>> {
        return boreholeRepository.getAllBoreholes()
    }
}