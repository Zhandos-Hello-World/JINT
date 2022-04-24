package com.example.myapplication.listFragment

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.room.Currency

class CurrencyDiffItemCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }
}