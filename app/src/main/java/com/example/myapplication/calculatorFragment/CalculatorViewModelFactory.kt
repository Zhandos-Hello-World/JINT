package com.example.myapplication.calculatorFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.room.CurrencyDao
import java.lang.IllegalArgumentException

class CalculatorViewModelFactory(val dao: CurrencyDao, val id: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(dao, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}