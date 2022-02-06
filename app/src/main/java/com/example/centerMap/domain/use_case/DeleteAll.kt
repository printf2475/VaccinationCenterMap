package com.example.centerMap.domain.use_case

import com.example.centerMap.domain.repository.Repository
import javax.inject.Inject

class DeleteAll @Inject constructor(private val repository: Repository) {
    operator fun invoke(){
        repository.deleteAll()
    }
}