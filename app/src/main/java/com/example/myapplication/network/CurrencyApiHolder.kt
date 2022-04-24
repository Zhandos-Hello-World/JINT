package com.example.myapplication.network

import retrofit2.Response
import retrofit2.http.GET

interface CurrencyApiHolder {

    @GET("/daily_json.js")
    suspend fun getAllCurrencyResponse(): ResponseData
}