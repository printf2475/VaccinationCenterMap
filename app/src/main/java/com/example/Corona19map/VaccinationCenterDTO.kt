package com.example.Corona19map

import com.example.Corona19map.Room.Data

data class VaccinationCenterDTO(
    val currentCount: Int,
    val `data`: List<Data>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int
)
