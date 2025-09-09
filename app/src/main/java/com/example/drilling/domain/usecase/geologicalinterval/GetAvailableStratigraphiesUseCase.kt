package com.example.drillingapp.domain.usecase.geologicalinterval

import com.example.drilling.domain.repository.GeologicalIntervalRepository
import javax.inject.Inject

class GetAvailableStratigraphiesUseCase @Inject constructor(
    private val intervalRepository: GeologicalIntervalRepository
) {
    suspend operator fun invoke(): List<String> {
        return intervalRepository.getAvailableStratigraphies()
    }
}