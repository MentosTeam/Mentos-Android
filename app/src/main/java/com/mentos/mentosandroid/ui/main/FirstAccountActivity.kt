package com.mentos.mentosandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mentos.mentosandroid.databinding.ActivityFirstAccountBinding

class FirstAccountActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}