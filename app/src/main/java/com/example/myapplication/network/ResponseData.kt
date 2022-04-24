package com.example.myapplication.network

import com.squareup.moshi.Json


class ResponseData {
    @Json(name="Date")
    var date: String = ""
    @Json(name="PreviousDate")
    private var previousDate: String = ""
    @Json(name="PreviousURL")
    private var previousUrl: String = ""
    @Json(name = "Timestamp")
    private var timeStamp: String = ""
    @Json(name = "Valute")
    var valute = mapOf<String, Currency>()
}