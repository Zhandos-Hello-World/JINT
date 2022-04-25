package com.example.myapplication.network

import retrofit2.http.GET

interface CurrencyApiHolder {

    @GET("/latest.js")
    suspend fun getAllCurrencyResponse(): ResponseData
}