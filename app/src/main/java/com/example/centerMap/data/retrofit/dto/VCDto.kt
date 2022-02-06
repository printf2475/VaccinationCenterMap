package com.example.centerMap.data.retrofit.dto

data class VCDto(
    val currentCount: Int,
    val `data`: List<VaccinationCenterData>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)
