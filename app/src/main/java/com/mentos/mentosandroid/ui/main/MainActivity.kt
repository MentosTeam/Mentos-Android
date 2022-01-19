package com.mentos.mentosandroid.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var selectedBottomNaviPosition = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        binding.mainBottomNavi.selectedItemId = when (selectedBottomNaviPosition) {
            0 -> R.id.searchFragment
            1 -> R.id.stateFragment
            2 -> R.id.homeFragment
            3 -> R.id.profileFragment
            4 -> R.id.settingFragment
            else -> R.id.homeFragment
        }
    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.searchFragment -> selectedBottomNaviPosition = 0
                R.id.stateFragment -> selectedBottomNaviPosition = 1
                R.id.homeFragment -> selectedBottomNaviPosition = 2
                R.id.profileFragment -> selectedBottomNaviPosition = 3
                R.id.settingFragment -> selectedBottomNaviPosition = 4
            }
        }
        binding.mainBottomNavi.setupWithNavController(navController)
    }
}