package com.example.Corona19map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.room.Room
import com.example.Corona19map.Room.DataBaseVC
import com.example.Corona19map.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel : ViewModelVC  by viewModels()
        viewModel.getVaccinationCenterData()

        viewModel.getVCList().observe(this, {
            for (i in 0..99)
            Log.i("옵저버"+i, it[i].toString())

        })
    }
}