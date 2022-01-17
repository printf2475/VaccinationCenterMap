package com.example.Corona19map

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitCorona {
    @GET("15077586/v1/centers")
    fun getVaccinationCenter(@Query("page") page: Int,
                             @Query("perPage") perPage: Int,
                             @Query("serviceKey") serviceKey: String): Call<VaccinationCenterDTO>

}