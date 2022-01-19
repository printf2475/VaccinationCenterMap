package com.example.centerMap

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitVC {
    @GET("15077586/v1/centers")
    fun getVaccinationCenter(@Query("page") page: Int,
                             @Query("perPage") perPage: Int,
                             @Query("serviceKey") serviceKey: String): Call<VCDAO>

    companion object{
        fun getInstence()
        : RetrofitVC {
            return Retrofit.Builder().baseUrl("https://api.odcloud.kr/api/")
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(RetrofitVC::class.java)
        }
    }

}