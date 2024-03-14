package com.example.rapgenerator

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.rapgenerator.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    fun setFullscreenFlags() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
    }

    fun changeFullScreenFlags(isFullSize: Boolean) {
        if (isFullSize) window.addFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        ) else window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}