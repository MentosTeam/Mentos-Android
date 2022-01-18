package com.mentos.mentosandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}