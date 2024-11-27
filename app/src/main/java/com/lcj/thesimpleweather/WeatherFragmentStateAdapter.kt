package com.lcj.thesimpleweather

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.lcj.thesimpleweather.fragment.WeatherHomeFragment
import com.lcj.thesimpleweather.fragment.WeatherListFragment

class WeatherFragmentStateAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> WeatherHomeFragment()
            else -> WeatherListFragment()
        }
    }
}