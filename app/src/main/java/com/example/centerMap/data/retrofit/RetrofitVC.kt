package com.example.centerMap.data.retrofit

import com.example.centerMap.data.retrofit.dto.VCDto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitVC {
    @GET("15077586/v1/centers")
    fun getVaccinationCenter(@Query("page") page: Int,
                             @Query("perPage") perPage: Int,
                             @Query("serviceKey") serviceKey: String): VCDto

    companion object{
        const val BASE_URL="https://api.odcloud.kr/api/"
        const val perPage = 10
        const val key =
            "bNmSjmL3NWL/mAmsQV0SyDT+8DCdZckhVg5/tSsmJHa47eBZBE+aFvCHYxeM1Dsz2FcgQ64elqYL3mr6GUyjOg=="

    }

}