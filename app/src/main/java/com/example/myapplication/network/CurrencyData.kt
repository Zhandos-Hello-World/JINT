package com.example.myapplication.network

import com.squareup.moshi.Json


class CurrencyData {
    @Json(name = "disclaimer")
    val disclaimer: String = ""
    @Json(name = "date")
    val date: String = ""
    @Json(name = "timestamp")
    val timestamp: Long = 0L
    @Json(name = "base")
    val base: String = ""
    @Json(name = "rates")
    var rates = mapOf<String, Double>()
}