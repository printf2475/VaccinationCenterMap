package com.example.centerMap.data.repository

import com.example.centerMap.data.retrofit.RetrofitVC
import com.example.centerMap.data.retrofit.RetrofitVC.Companion.key
import com.example.centerMap.data.retrofit.RetrofitVC.Companion.perPage
import com.example.centerMap.data.retrofit.dto.VCDto
import com.example.centerMap.data.room.DataBaseVC
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData
import com.example.centerMap.domain.repository.Repository
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val dao: DataBaseVC, private val retrofitVC: RetrofitVC) : Repository {

    override fun insert(vaccinationCenterData: VaccinationCenterData) {
        dao.vaccinavionCenterDTO().insertData(vaccinationCenterData)
    }

    override fun deleteAll() {
        dao.vaccinavionCenterDTO().deleteAll()
    }

    override fun getAll(): List<VaccinationCenterData> {
        return dao.vaccinavionCenterDTO().getAll()
    }

    override suspend fun getVCData(page : Int): VCDto {
        return retrofitVC.getVaccinationCenter(page, perPage, key)
    }

}