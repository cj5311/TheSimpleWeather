package com.lcj.thesimpleweather.model


import com.google.gson.annotations.SerializedName
import com.lcj.thesimpleweather.RainStatus
import com.lcj.thesimpleweather.SkyStatus
import com.lcj.thesimpleweather.WeatherData

data class WeatherModel(
    @SerializedName("response")
    val response: Response
){
    fun toWeatherData(): WeatherData {
        return response.body.items.item.toWeatherData()
    }

    private fun List<Item>.toWeatherData(): WeatherData {
        val items = this
        val time = items.find { it.category == "SKY" }?.fcstTime ?: "--:--"
        val skyStatus = items.find { it.category == "SKY" }?.fcstValue ?: ""
        val rainStatus = items.find { it.category == "PTY" }?.fcstValue ?: ""
        val rainPercent = items.find { it.category == "POP" }?.fcstValue ?: ""
        val rainAmount = items.find { it.category == "PCP" }?.fcstValue ?: ""
        val temp = items.find { it.category == "TMP" }?.fcstValue ?: ""
        val windSpeed = items.find { it.category == "WSD" }?.fcstValue ?: ""
        val humidity = items.find { it.category == "REH" }?.fcstValue ?: ""

        return WeatherData(
            time = time,
            skyStatus = SkyStatus.entries.firstOrNull { it.value == skyStatus.toInt() }
                ?: SkyStatus.GOOD,
            rainAmount = rainAmount,
            rainPercent = rainPercent,
            rainState = RainStatus.entries.firstOrNull { it.value == rainStatus.toInt() }
                ?: RainStatus.NONE,
            temperature = temp,
            windSpeed = windSpeed,
            humidity = humidity
        )
    }
}