package com.pvsb.customprogressbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.pvsb.customprogressbutton.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pbLogin.setOnClickListener {

            binding.pbLogin.setLoading()

            lifecycleScope.launch {
                delay(2000)
                binding.pbLogin.setNormal()
            }
        }
    }
}