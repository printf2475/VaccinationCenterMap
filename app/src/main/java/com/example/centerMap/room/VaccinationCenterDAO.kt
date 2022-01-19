package com.example.centerMap.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VaccinationCenterDAO {
    @Query("SELECT * FROM VaccinationCenterData")
    fun getAll(): List<VaccinationCenterData>

    @Insert
    fun insertData(vaccinationCenterData: VaccinationCenterData)

    @Query("DELETE FROM VaccinationCenterData")
    fun deleteAll()
}