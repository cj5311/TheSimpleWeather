package com.lcj.thesimpleweather.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.lcj.thesimpleweather.databinding.FragmentWeatherHomeBinding
import com.lcj.thesimpleweather.veiwmodel.WeatherViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import com.lcj.thesimpleweather.R
import com.lcj.thesimpleweather.data.cityList
import com.lcj.thesimpleweather.databinding.PopupListBinding

class WeatherHomeFragment : Fragment() {
    private var _binding: FragmentWeatherHomeBinding? = null
    private val binding get() = _binding!!
    private val currentDate by lazy { SimpleDateFormat("yyyyMMdd", Locale.KOREA).format(Date()) }
    private val viewModel by activityViewModels<WeatherViewModel>()

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

        binding.selectRegionButton.setOnClickListener {
            showCitiesPopup(it)
        }

        binding.selectedRegionText.setOnClickListener {
            showCitiesPopup(it)
        }

        viewModel.weatherData.observe(viewLifecycleOwner) { data ->
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
        fetchWeatherData()
    }

    private fun showCitiesPopup(anchorView: View) {
        val cities = cityList.map { it.name }.toList()

        val popupBinding = PopupListBinding.inflate(layoutInflater, null, false).also {
            it.cityList.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cities)
        }

        PopupWindow(popupBinding.root, 500, LinearLayout.LayoutParams.WRAP_CONTENT, true).apply {
            setBackgroundDrawable(getDrawable(requireContext(), R.drawable.rectangle_background))
            elevation = 10f
            showAsDropDown(anchorView)
            popupBinding.cityList.setOnItemClickListener { _, _, position, _ ->
                val selectedCity = cities[position]
                binding.selectedRegionText.text = selectedCity
                viewModel.setRegion(selectedCity)
                fetchWeatherData(selectedCity)
                dismiss()
            }
        }
    }

    private fun fetchWeatherData(city: String = "서울특별시") {
        viewModel.getWeather(currentDate, city)
    }
}