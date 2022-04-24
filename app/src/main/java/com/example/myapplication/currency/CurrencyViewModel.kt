package com.example.myapplication.currency

import androidx.lifecycle.*
import com.example.myapplication.network.Currency
import com.example.myapplication.network.CurrencyApi
import com.example.myapplication.network.ResponseData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class CurrencyViewModel: ViewModel() {
    private val _response = MutableLiveData<ResponseData>()
    private val _currencies = Transformations.map(_response) {
        it.valute.values.toList()
    }
    val response: LiveData<ResponseData> = _response
    val currencies: LiveData<List<Currency>> = _currencies
    val currency1 = Transformations.map(currencies) { it[0] }
    val currency2 = Transformations.map(currencies) { it[1] }



    init {
        getCurrency()
    }

    private fun getCurrency() {
        viewModelScope.launch {
            _response.value = CurrencyApi.retrofitService.getAllCurrencyResponse()
        }
    }
}