package com.example.centerMap.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData
import com.example.centerMap.domain.model.VCData

@Dao
interface VaccinationCenterDAO {
    @Query("SELECT * FROM VCData")
    fun getAll(): List<VCData>

    @Insert
    fun insertData(vcData: VCData)

    @Query("DELETE FROM VCData")
    fun deleteAll()
}