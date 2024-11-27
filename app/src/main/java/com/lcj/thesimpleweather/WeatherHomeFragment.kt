package com.lcj.thesimpleweather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WeatherHomeFragment : Fragment() {

    private var _binding: FragmentWeatherHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherHomeBinding.inflate(inflater, container, false)
        with(binding) {
            // 임시로 현재 날씨는 0번 Dummy Data로 설정
            val data = WeatherDataList.list.get(0)
            mainWeatherText.text = data.skyStatus.text
            mainTemperTv.text = data.temperature
            mainRainTv.text = data.rainState.value.toString()
            mainWaterTv.text = data.humidity
            mainWindTv.text = data.windSpeed
            mainRainPercentTv.text = getString(R.string.rain_percent, data.rainPercent)
            rainStatusIv.setImageResource(data.rainState.icon)
            weatherStatusIv.setImageResource(data.skyStatus.colorIcon)
        }
        return binding.root
    }
}