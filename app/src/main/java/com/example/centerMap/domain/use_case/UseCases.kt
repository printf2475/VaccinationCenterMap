package com.example.centerMap.domain.use_case

import javax.inject.Inject

data class UseCases @Inject constructor(
    val deleteAll: DeleteAll,
    val getAllData: GetAllData,
    val getVCData: GetVCData,
    val insertData: InsertData
)
