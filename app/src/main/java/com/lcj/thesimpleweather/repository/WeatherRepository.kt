package com.lcj.thesimpleweather.repository

import com.lcj.thesimpleweather.data.cityList
import com.lcj.thesimpleweather.model.WeatherModel
import com.lcj.thesimpleweather.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {
    suspend fun getWeather(date: String, cityName: String, pageNo: Int = 1): WeatherModel {
        return withContext(Dispatchers.IO) {
            val city = cityList.find { it.name == cityName } ?: cityList.first()
            RetrofitInstance.service.getWeather(
                baseDate = date.toInt(),
                nx = city.nx.toString(),
                ny = city.ny.toString(),
                numOfRows = pageNo * 12
            )
        }
    }
}