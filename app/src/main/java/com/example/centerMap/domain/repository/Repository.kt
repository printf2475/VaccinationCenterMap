package com.example.centerMap.domain.repository

import com.example.centerMap.data.retrofit.dto.VCDto
import com.example.centerMap.domain.model.VCData

interface Repository {
    fun insert(vcData: VCData)

    fun deleteAll()

    fun getAll(): List<VCData>

    suspend fun getVCData(page: Int): VCDto
}