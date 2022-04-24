package com.example.myapplication.request

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetworkService {
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJSONApi(): JsonPlaceHolderApi {
        return retrofit.create(JsonPlaceHolderApi::class.java)
    }

    companion object {
        @Volatile
        private var INSTANCE: NetworkService? = null
        private const val BASE_URL = "https://www.cbr-xml-daily.ru/"

        fun getInstance(): NetworkService {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = NetworkService()
                }
                return INSTANCE as NetworkService
            }
        }
    }
}