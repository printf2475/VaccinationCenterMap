package com.example.centerMap.domain.repository

import com.example.centerMap.data.retrofit.dto.VCDto
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData

interface Repository {
    fun insert(vaccinationCenterData: VaccinationCenterData)

    fun deleteAll()

    fun getAll(): List<VaccinationCenterData>

    suspend fun getVCData(page: Int): VCDto
}