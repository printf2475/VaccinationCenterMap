package com.example.centerMap

import com.example.centerMap.room.VaccinationCenterData

data class VCDAO(
    val currentCount: Int,
    val `data`: List<VaccinationCenterData>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)
