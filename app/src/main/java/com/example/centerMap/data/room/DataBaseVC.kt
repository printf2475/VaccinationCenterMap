package com.example.centerMap.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.centerMap.domain.model.VCData

@Database(entities = [VCData::class], version = 2)
abstract class DataBaseVC : RoomDatabase() {
    abstract fun vaccinavionCenterDTO() : VaccinationCenterDAO


    companion object {
        const val DATABASE_NAME ="ViccinavionDB"
    }
}