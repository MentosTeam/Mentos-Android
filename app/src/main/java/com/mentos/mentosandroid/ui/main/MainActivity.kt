package com.mentos.mentosandroid.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var selectedBottomNaviPosition = 2

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        setIntent(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()

        Log.d("new intent", "oncreate")
        if (intent != null) {
            Log.d("new intent", "인텐트")
            Log.d("new intent", intent.getIntExtra("Destination", 2).toString())
            binding.mainBottomNavi.selectedItemId =
                when (intent.getIntExtra("Destination", 2)) {
                    0 -> R.id.searchFragment
                    1 -> R.id.stateFragment
                    2 -> R.id.homeFragment
                    3 -> R.id.profileFragment
                    4 -> R.id.settingFragment
                    else -> R.id.homeFragment
                }
        }
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
                R.id.searchFragment -> showBottomNavi(0)
                R.id.stateFragment -> showBottomNavi(1)
                R.id.homeFragment -> showBottomNavi(2)
                R.id.profileFragment -> showBottomNavi(3)
                R.id.settingFragment -> showBottomNavi(4)
                else -> hideBottomNavi()
            }
        }
        binding.mainBottomNavi.setupWithNavController(navController)
    }

    private fun hideBottomNavi() {
        binding.mainBottomNavi.visibility = View.GONE
    }

    private fun showBottomNavi(position: Int) {
        selectedBottomNaviPosition = position
        binding.mainBottomNavi.visibility = View.VISIBLE
    }
}