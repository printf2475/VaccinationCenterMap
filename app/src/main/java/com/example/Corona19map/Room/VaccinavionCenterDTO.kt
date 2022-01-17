package com.example.Corona19map.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VaccinavionCenterDTO {
    @Query("SELECT * FROM Data")
    fun getAll(): List<Data>

    @Insert
    fun insertData(data: Data)

    @Query("DELETE FROM Data")
    fun deleteAll()
}