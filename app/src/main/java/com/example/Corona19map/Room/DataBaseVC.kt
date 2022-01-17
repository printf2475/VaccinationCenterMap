package com.example.Corona19map.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1)
abstract class DataBaseVC : RoomDatabase() {
    abstract fun vaccinavionCenterDTO() : VaccinavionCenterDTO


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DataBaseVC? = null

        fun getDatabase(context: Context): DataBaseVC {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBaseVC::class.java,
                    "ViccinavionDB"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}