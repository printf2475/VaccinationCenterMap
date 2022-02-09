package com.example.centerMap.data.repository

import android.util.Log
import com.example.centerMap.data.retrofit.RetrofitVC
import com.example.centerMap.data.retrofit.RetrofitVC.Companion.key
import com.example.centerMap.data.retrofit.RetrofitVC.Companion.perPage
import com.example.centerMap.data.retrofit.dto.VCDto
import com.example.centerMap.data.room.DataBaseVC
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData
import com.example.centerMap.domain.model.VCData
import com.example.centerMap.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val dao: DataBaseVC, private val retrofitVC: RetrofitVC) : Repository {

    override fun insert(vcData: VCData) {
        dao.vaccinavionCenterDTO().insertData(vcData)
    }

    override fun deleteAll() {
        dao.vaccinavionCenterDTO().deleteAll()
    }

    override fun getAll(): List<VCData> {
        return dao.vaccinavionCenterDTO().getAll()
    }

    override suspend fun getVCData(page : Int): VCDto {
        return retrofitVC.getVaccinationCenter(page, perPage, key)
    }

}