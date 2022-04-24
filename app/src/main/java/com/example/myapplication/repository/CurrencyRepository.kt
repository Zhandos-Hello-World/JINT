package com.example.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.request.JsonPlaceHolderApi
import com.example.myapplication.request.ResponseData
import com.example.myapplication.room.Currency
import com.example.myapplication.room.CurrencyDatabase


class CurrencyRepository(
    private val api: JsonPlaceHolderApi,
    val currencyDatabase: CurrencyDatabase
) {
    val currencyLiveData = MutableLiveData<ResponseData>()

    suspend fun getCurrency() {
        val result = api.getAllValuteResponse()

        if (result.body() != null) {
            val list = mutableListOf<Currency>()
            val map = result.body()!!.valute
            for (i in map.keys) {
                if (map[i] != null) {
                    list.add(map[i]!!)
                }
            }
            currencyDatabase.currencyDao.insertAll(list)

            currencyLiveData.postValue(result.body())
        }
    }

}