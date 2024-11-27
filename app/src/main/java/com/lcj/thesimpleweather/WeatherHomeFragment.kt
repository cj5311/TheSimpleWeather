package com.lcj.thesimpleweather
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.lcj.thesimpleweather.databinding.FragmentWeatherHomeBinding
import com.lcj.thesimpleweather.repository.WeatherRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WeatherHomeFragment : Fragment() {
    //바인딩 객체 정의
    private var _binding: FragmentWeatherHomeBinding? = null
    private val binding get() = _binding!!

    // 날짜 변환함수
    private val currentDate by lazy {
        SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(Date())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = WeatherRepository()
        lifecycleScope.launch {
            val model = repository.getWeather(currentDate, "서울특별시")
            val data = model.toWeatherData()
            with(binding) {
                mainWeatherText.text = data.skyStatus.text
                mainTemperTv.text = data.temperature
                mainRainTv.text = data.rainState.value.toString()
                mainWaterTv.text = data.humidity
                mainWindTv.text = data.windSpeed
                mainRainPercentTv.text = getString(R.string.rain_percent, data.rainPercent)
                rainStatusIv.setImageResource(data.rainState.icon)
                weatherStatusIv.setImageResource(data.skyStatus.colorIcon)
            }
        }
    }
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentWeatherHomeBinding.inflate(inflater, container, false)
//        with(binding) {
//            // 임시로 현재 날씨는 0번 Dummy Data로 설정
//            val data = WeatherDataList.list.get(0)
//            mainWeatherText.text = data.skyStatus.text
//            mainTemperTv.text = data.temperature
//            mainRainTv.text = data.rainState.value.toString()
//            mainWaterTv.text = data.humidity
//            mainWindTv.text = data.windSpeed
//            mainRainPercentTv.text = getString(R.string.rain_percent, data.rainPercent)
//            rainStatusIv.setImageResource(data.rainState.icon)
//            weatherStatusIv.setImageResource(data.skyStatus.colorIcon)
//        }
//        return binding.root
//    }
}