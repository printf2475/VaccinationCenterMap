package com.example.centerMap.domain.model

data class VCData(
    val address: String,
    val facilityName: String,
    val phoneNumber: String
){
    override fun toString(): String {
        return "주소 : ${this.address} ${this.facilityName}\n전화번호 : ${this.phoneNumber}\n"
    }
}
