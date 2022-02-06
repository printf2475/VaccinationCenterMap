package com.example.centerMap.domain.use_case

import com.example.centerMap.data.retrofit.dto.VaccinationCenterData
import com.example.centerMap.domain.repository.Repository
import javax.inject.Inject

class GetAllData @Inject constructor(private val repository: Repository) {
    operator fun invoke(): List<VaccinationCenterData> {
        return repository.getAll()
    }
}