package com.example.centerMap.presentation.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.centerMap.R
import com.example.centerMap.presentation.map.ViewModelVC
import com.example.centerMap.databinding.ActivitySplashBinding
import com.example.centerMap.presentation.map.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: ViewModelVC by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this,
            R.layout.activity_splash
        )


        binding.lifecycleOwner = this@SplashActivity
        binding.viewModel = viewModel

        viewModel.getProgressCount().observe(this@SplashActivity) {
            if (it >= 100) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        viewModel.getVaccinationCenterData()
        viewModel.startProgress()

    }
}