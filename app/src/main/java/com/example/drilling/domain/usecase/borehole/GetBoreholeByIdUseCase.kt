package com.example.drilling.domain.usecase.borehole

import com.example.drilling.domain.model.Borehole
import com.example.drilling.domain.repository.BoreholeRepository
import javax.inject.Inject

class GetBoreholeByIdUseCase @Inject constructor(
    private val boreholeRepository: BoreholeRepository
) {
    suspend operator fun invoke(id: Long): Borehole? {
        return boreholeRepository.getBoreholeById(id)
    }
}