package com.example.Corona19map

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.Corona19map.Room.Data
import com.example.Corona19map.Room.DataBaseVC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelVC(application: Application) : AndroidViewModel(application) {
    private val perPage = 10;
    private val totalDataCount = 100;
    private val key = "bNmSjmL3NWL/mAmsQV0SyDT+8DCdZckhVg5/tSsmJHa47eBZBE+aFvCHYxeM1Dsz2FcgQ64elqYL3mr6GUyjOg=="
    private val db = DataBaseVC.getDatabase(application)
    private val retrofit = Retrofit.Builder().baseUrl("https://api.odcloud.kr/api/")
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(RetrofitCorona::class.java)

    private var VCList = MutableLiveData<List<Data>>()

    fun getVCList(): MutableLiveData<List<Data>> {
        return VCList
    }

    fun getVaccinationCenterData() {

        CoroutineScope(Dispatchers.IO).launch {
            db.vaccinavionCenterDTO().deleteAll()
        }
        for(i: Int in 1..10){
            retrofit.getVaccinationCenter(i, perPage, key).enqueue(object : Callback<VaccinationCenterDTO> {
                override fun onResponse(
                    call: Call<VaccinationCenterDTO>,
                    response: Response<VaccinationCenterDTO>
                ) {
                    CoroutineScope(Dispatchers.IO).launch {
                        for(j: Int in 0..9) {
                            db.vaccinavionCenterDTO().insertData(response.body()!!.data[j])
                          
                        }
                        if (i*perPage==totalDataCount){
                            Log.e("갯수", (i*perPage).toString())
                            VCList.postValue(db.vaccinavionCenterDTO().getAll())
                        }
                    }
                }

                override fun onFailure(call: Call<VaccinationCenterDTO>, t: Throwable) {
                    Log.e("값받아오기", "실패"+t.message)
                }
            })
        }



    }




}