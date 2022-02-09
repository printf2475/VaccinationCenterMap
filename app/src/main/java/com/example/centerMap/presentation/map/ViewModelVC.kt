package com.example.centerMap.presentation.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.centerMap.Resource
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData
import com.example.centerMap.domain.model.VCData
import com.example.centerMap.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.concurrent.thread

@HiltViewModel
class ViewModelVC @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: UseCases) :
    ViewModel() {
    private var isFinished = false
    private var flag = false
    private var count = 0


    private var VCList = MutableLiveData<List<VCData>>()

    private var progressCount = MutableLiveData<Int>()

    init {
        progressCount.value=count
    }



    private fun progressRun() {
        while (!flag) {
            progressCount.postValue(count++)
            if (progressCount.value!! >= 79) {
                flag = true
            }
            Thread.sleep(20)
        }
        flag = false
    }

    private fun finishedGetData() {
        while (!flag) {
            progressCount.postValue(count++)
            if (progressCount.value!! >= 100) {
                flag = true
            }
            Thread.sleep(20)
        }
    }

    private fun notFinishedGetData() {
        while (!flag) {
            if (useCases.getAllData().size >= 100) {
                while (!flag) {
                    progressCount.postValue(count++)
                    if (progressCount.value!! >= 100) {
                        flag = true
                    }
                    Thread.sleep(35)
                }
            }
        }
    }

    fun getProgressCount(): MutableLiveData<Int> {
        return progressCount
    }

    fun getVCDataFromDB(): MutableLiveData<List<VCData>> {
        CoroutineScope(Dispatchers.IO).launch {
            VCList.postValue(useCases.getAllData())
        }
        return VCList
    }


    fun getVaccinationCenterData() {
        CoroutineScope(Dispatchers.Default).launch {
            progressRun()
            if (isFinished){
                finishedGetData()
            }

            if (!isFinished){
                notFinishedGetData()
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            useCases.getVCData().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.forEach {
                        useCases.insertData(it)
                        }
                        isFinished=true

                    }

                    is Resource.Loading -> {
                        isFinished=false
                    }
                }
            }.launchIn(viewModelScope)
        }

    }
}