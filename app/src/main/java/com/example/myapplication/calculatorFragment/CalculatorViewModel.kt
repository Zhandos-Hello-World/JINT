package com.example.myapplication.calculatorFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.room.Currency
import com.example.myapplication.room.CurrencyDao

class CalculatorViewModel(dao: CurrencyDao, val id: Long): ViewModel() {
    var currency: LiveData<Currency> = dao.get(id)
    var rub = MutableLiveData("1.0")
    var current = MutableLiveData("1.0")

    fun rubToCurrent(): Float {

        val nominal = currency.value?.nominal?.toDouble()
        val value = currency.value?.value
        return value?.div(nominal ?: 1.0)?.toFloat() ?: 0.0f
    }

    fun currentToRub() = 1.0f.div(rubToCurrent())

    fun rubToCurr(rub: String): String {
        val value = currency.value?.value
        if (value == null || value == 0.0) {
            return ""
        }
        return String.format("%.4f", rub.toDouble().div(value))
    }

    fun currToRub(current: String): String {
        val value = currency.value?.value
        if (value == null || value == 0.0) {
            return ""
        }
        return String.format("%.4f", current.toDouble() * value)
    }
}