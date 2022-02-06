package com.example.centerMap.data.retrofit.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.centerMap.domain.model.VCData

@Entity
data class VaccinationCenterData(
    val address: String,
    val centerName: String,
    val centerType: String,
    val createdAt: String,
    val facilityName: String,
    val id: Int,
    val lat: String,
    val lng: String,
    val org: String,
    val phoneNumber: String,
    val sido: String,
    val sigungu: String,
    val updatedAt: String,
    val zipCode: String
){
    @PrimaryKey(autoGenerate = true)
    var dataId: Int = 0

    override fun toString(): String {
        return "주소 : ${this.address} ${this.facilityName}\n전화번호 : ${this.phoneNumber}\n"
    }

    fun toVCData(): VCData {
        return VCData(address = address, facilityName = facilityName, phoneNumber = phoneNumber)
    }

}
