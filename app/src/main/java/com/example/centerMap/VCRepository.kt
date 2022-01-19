package com.example.centerMap

import android.util.Log
import com.example.centerMap.room.DataBaseVC
import com.example.centerMap.room.VaccinationCenterData
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VCRepository(private val dao: DataBaseVC) {
    private val perPage = 10
    private val key =
        "bNmSjmL3NWL/mAmsQV0SyDT+8DCdZckhVg5/tSsmJHa47eBZBE+aFvCHYxeM1Dsz2FcgQ64elqYL3mr6GUyjOg=="

    val retrofit = RetrofitVC.getInstence()

    fun insert(vaccinationCenterData: VaccinationCenterData) {
        dao.vaccinavionCenterDTO().insertData(vaccinationCenterData)
    }

    fun deleteAll() {
        dao.vaccinavionCenterDTO().deleteAll()
    }

    fun getAll(): List<VaccinationCenterData> {
        return dao.vaccinavionCenterDTO().getAll()
    }

    fun getVCData(){
        CoroutineScope(Dispatchers.IO).launch {
            deleteAll()

            for (page: Int in 1..perPage) {
                retrofit.getVaccinationCenter(page, perPage, key).enqueue(object : Callback<VCDAO> {
                    override fun onResponse(
                        call: Call<VCDAO>,
                        response: Response<VCDAO>
                    ) {
//                        Log.e("값받아오기", response.body()?.data.toString())
                        response.body()?.data?.forEach {
                            insert(it)
                        }
                    }

                    override fun onFailure(call: Call<VCDAO>, t: Throwable) {
                        Log.e("값받아오기", "실패" + t.message)
                    }
                })

            }
        }
    }

}