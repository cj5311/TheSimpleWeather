package com.lcj.thesimpleweather

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.lcj.thesimpleweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            val adapter = WeatherFragmentStateAdapter(this@MainActivity)
            viewPager.adapter = adapter
            indicator.setViewPager(viewPager)
            adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
//            viewPager.currentItem = 1
        }
    }
}