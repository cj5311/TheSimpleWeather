package com.lcj.thesimpleweather.network

import com.lcj.thesimpleweather.model.WeatherModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query

// 배포시에는 안전하게 보완 적용 필요
// 공공 데이터 포탈에서 발급 받은 자신만의 API키를 입력해 주세요.
private const val API_KEY =
    "pVwnUt6RzMm5u3FaQN2lGB83VAkVOPbSi5mgMO5l%2Btn7BrcIVK%2Fjv6S2hSM41XotFqLN0a0kQ9mspdvSwm9kkw%3D%3D"

interface WeatherService {

    @GET("getVilageFcst?serviceKey=$API_KEY")
    suspend fun getWeather(
        @Query("base_date") baseDate: Int,
        @Query("base_time") baseTime: String = "0500",
        @Query("nx") nx: String = "60",
        @Query("ny") ny: String = "121",
        @Query("numOfRows") numOfRows: Int = 12,
        @Query("pageNo") pageNo: Int = 1,
        @Query("dataType") dataType: String = "JSON"
    ): WeatherModel
}