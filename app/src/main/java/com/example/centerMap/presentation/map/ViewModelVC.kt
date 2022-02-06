package com.example.centerMap.presentation.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.centerMap.data.retrofit.dto.VaccinationCenterData
import com.example.centerMap.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.concurrent.thread

@HiltViewModel
class ViewModelVC @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: UseCases) :
    ViewModel() {
    private var isFinished = false
    private var count = 0


    private var VCList = MutableLiveData<List<VaccinationCenterData>>()

    private var progressCount = MutableLiveData<Int>()

    fun startProgress() {
        count = 0
        progressCount.value=count

        thread(start = true) {

            progressRun()

            isFinished = false

            if (useCases.getAllData().size <= 100) {
                notFinishedGetData()
            }

            if (useCases.getAllData().size > 100) {
                finishedGetData()

            }
        }
    }

    private fun progressRun() {
        while (!isFinished) {
            progressCount.postValue(count++)
            if (progressCount.value!! >= 79) {
                isFinished = true
            }
            Thread.sleep(20)
        }
    }

    private fun finishedGetData() {
        while (!isFinished) {
            progressCount.postValue(count++)
            if (progressCount.value!! >= 100) {
                isFinished = true
            }
            Thread.sleep(20)
        }
    }

    private fun notFinishedGetData() {
        while (!isFinished) {
            if (useCases.getAllData().size <= 100) {
                while (!isFinished) {
                    progressCount.postValue(count++)
                    if (progressCount.value!! >= 100) {
                        isFinished = true
                    }
                    Thread.sleep(35)
                }
            }
        }
    }

    fun getProgressCount(): MutableLiveData<Int> {
        return progressCount
    }

    fun getVCDataFromDB(): MutableLiveData<List<VaccinationCenterData>> {
        CoroutineScope(Dispatchers.IO).launch {
            VCList.postValue(useCases.getAllData())
        }
        return VCList
    }

    fun getVaccinationCenterData() {

    }
}