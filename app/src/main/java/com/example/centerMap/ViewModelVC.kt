package com.example.centerMap

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.centerMap.room.VaccinationCenterData
import kotlinx.coroutines.*
import kotlin.concurrent.thread


class ViewModelVC(private val repository: VCRepository) :
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

            if (repository.getAll().size <= 100) {
                notFinishedGetData()
            }

            if (repository.getAll().size > 100) {
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
            if (repository.getAll().size <= 100) {
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
            VCList.postValue(repository.getAll())
        }
        return VCList
    }

    fun getVaccinationCenterData() {
        repository.getVCData()
    }
}