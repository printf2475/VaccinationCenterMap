package com.example.centerMap.domain.use_case

import com.example.centerMap.data.retrofit.RetrofitVC.Companion.perPage
import com.example.centerMap.data.retrofit.dto.VCDto
import com.example.centerMap.domain.model.VCData
import com.example.centerMap.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetVCData @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): Flow<List<VCData>> = flow {
        for (page: Int in 1..perPage) {
           val VCData =  repository.getVCData(page).data.map { it.toVCData() }
            emit(VCData)
        }
    }
}