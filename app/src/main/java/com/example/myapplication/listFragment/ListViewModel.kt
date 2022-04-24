package com.example.myapplication.listFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.repository.CurrencyRepository
import com.example.myapplication.request.NetworkService
import com.example.myapplication.request.ResponseData
import com.example.myapplication.room.Currency
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope


class ListViewModel(val repository: CurrencyRepository): ViewModel() {
    val getAll get() = repository.currencyDatabase.currencyDao.getAll()

    init {

    }
}