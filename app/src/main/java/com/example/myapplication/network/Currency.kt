package com.example.myapplication.network

import com.squareup.moshi.Json

data class Currency(
    @Json(name="id")
    var id: String = "",
    @Json(name="NumCode")
    var numCode: String = "",
    @Json(name="CharCode")
    var charCode: String = "",
    @Json(name="Nominal")
    var nominal: Int = 0,
    @Json(name="Name")
    var name: String = "",
    @Json(name="Value")
    var value: Double = 0.0,
    @Json(name="Previous")
    var previous: Double = 0.0
)