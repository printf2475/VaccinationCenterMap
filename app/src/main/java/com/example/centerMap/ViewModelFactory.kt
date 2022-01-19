package com.example.centerMap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val repository: VCRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ViewModelVC::class.java)) {
            ViewModelVC(repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}