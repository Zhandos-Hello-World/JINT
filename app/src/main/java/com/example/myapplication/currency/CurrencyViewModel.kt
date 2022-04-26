package com.example.myapplication.currency

import androidx.lifecycle.*
import com.example.myapplication.network.CurrencyApi
import com.example.myapplication.network.CurrencyData
import kotlinx.coroutines.launch


class CurrencyViewModel : ViewModel() {
    private val _response = MutableLiveData<CurrencyData>()
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

        try {
            userInput.value?.let {
                currencies.value?.get(currency1.value)!!
                currencies.value?.get(currency2.value)!!
            }

            val result = (userInput.value?.toDouble() ?: 0.0) * (value2 / value1)

            converted.value = result.toString()
        } catch (ex: Exception) {
            converted.value = "Invalid input"
        }

    }
}