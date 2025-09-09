package com.example.drillingapp.domain.usecase.geologicalinterval

import com.example.drilling.domain.model.GeologicalInterval
import com.example.drilling.domain.repository.GeologicalIntervalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIntervalsByBoreholeIdUseCase @Inject constructor(
    private val intervalRepository: GeologicalIntervalRepository
){
    operator fun invoke(boreholeId: Long): Flow<List<GeologicalInterval>> {
        return intervalRepository.getIntervalsByBoreholeId(boreholeId)
    }
}