package com.example.centerMap.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData

@Database(entities = [VaccinationCenterData::class], version = 1)
abstract class DataBaseVC : RoomDatabase() {
    abstract fun vaccinavionCenterDTO() : VaccinationCenterDAO


    companion object {
        const val DATABASE_NAME ="ViccinavionDB"
    }
}