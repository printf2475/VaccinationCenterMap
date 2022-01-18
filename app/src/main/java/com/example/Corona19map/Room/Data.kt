package com.example.Corona19map.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
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
}
