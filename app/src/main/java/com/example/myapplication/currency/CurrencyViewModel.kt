package com.example.myapplication.currency

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.network.CurrencyApi
import com.example.myapplication.network.ResponseData
import kotlinx.coroutines.launch


class CurrencyViewModel : ViewModel() {
    private val _response = MutableLiveData<ResponseData>()
    val currencies = Transformations.map(_response) { it.rates }

    val currency1 = MutableLiveData("")
    val currency2 = MutableLiveData("")
    val userInput = MutableLiveData("")
    val converted = MutableLiveData("")

    init {
        getCurrency()
    }

    private fun getCurrency() {
        viewModelScope.launch {
            _response.value = CurrencyApi.retrofitService.getAllCurrencyResponse()
        }
    }
    fun convert() {
        val value1 = currencies.value?.get(currency1.value) ?: -1.0
        val value2 = currencies.value?.get(currency2.value) ?: -1.0
        val result = (userInput.value?.toDouble() ?: 0.0) * (value2 / value1)
        Log.d("Currency1", value1.toString())
        Log.d("Currency2", value2.toString())
        Log.d("CurrencyResult", (value2 / value1).toString())

        converted.value = result.toString()
    }
}