package com.example.myapplication.request

import com.example.myapplication.room.Currency
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseData {
    @SerializedName("Date")
    @Expose
    var date: String = ""
    @SerializedName("PreviousDate")
    @Expose
    private var previousDate: String = ""
    @SerializedName("PreviousURL")
    @Expose
    private var previousUrl: String = ""
    @SerializedName("Timestamp")
    @Expose
    private var timeStamp: String = ""
    @SerializedName("Valute")
    @Expose
    var valute = mapOf<String, Currency>()
}