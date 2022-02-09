package com.example.centerMap.data.retrofit.dto


import com.example.centerMap.domain.model.VCData


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
) {

    fun toVCData(): VCData {
        return VCData(
            centerName = centerName,
            address = address,
            facilityName = facilityName,
            centerType = centerType,
            phoneNumber = phoneNumber,
            lat = lat,
            lng = lng
        )
    }

}
