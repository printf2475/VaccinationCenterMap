package com.example.centerMap.domain.use_case

import com.example.centerMap.Resource
import com.example.centerMap.data.retrofit.RetrofitVC.Companion.perPage
import com.example.centerMap.domain.model.VCData
import com.example.centerMap.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVCData @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(): Flow<Resource<List<VCData>>> = flow {
        var dataList = mutableListOf<VCData>()
        emit(Resource.Loading())

        for (page: Int in 1..perPage) {
            val VCData =  repository.getVCData(page).data.map { it.toVCData() }
            VCData.forEach { dataList.add(it) }
        }

        emit(Resource.Success(dataList as List<VCData>))
    }
}