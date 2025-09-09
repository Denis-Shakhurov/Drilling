package com.example.drillingapp.domain.usecase.geologicalinterval

import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.repository.GeologicalIntervalRepository
import javax.inject.Inject

class GetIntervalByIdUseCase @Inject constructor(
    private val intervalRepository: GeologicalIntervalRepository
) {
    suspend operator fun invoke(id: Long): GeologicalInterval? {
        return intervalRepository.getIntervalById(id)
    }
}