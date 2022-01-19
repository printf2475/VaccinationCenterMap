package com.example.centerMap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.centerMap.room.DataBaseVC
import com.example.centerMap.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)

        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                VCRepository(DataBaseVC.getDatabase(this))
            )
        ).get(ViewModelVC::class.java)

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