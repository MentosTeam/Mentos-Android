package com.mentos.mentosandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mentos.mentosandroid.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}