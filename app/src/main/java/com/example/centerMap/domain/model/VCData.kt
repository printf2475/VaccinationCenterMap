package com.example.centerMap.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VCData(
    val centerName: String,
    val address: String,
    val facilityName: String,
    val centerType: String,
    val phoneNumber: String,
    val lat: String,
    val lng: String,
){
    @PrimaryKey(autoGenerate = true)
    var dataId: Int = 0


    override fun toString(): String {
        return "주소 : ${this.address} ${this.facilityName}\n전화번호 : ${this.phoneNumber}\n"
    }
}
