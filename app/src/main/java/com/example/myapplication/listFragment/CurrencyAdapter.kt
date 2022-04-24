package com.example.myapplication.listFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CurrencyItemBinding
import com.example.myapplication.room.Currency

class CurrencyAdapter(val clickListener: (currencyID: Long) -> Unit) :
    ListAdapter<Currency, CurrencyAdapter.CurrencyViewHolder>(CurrencyDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CurrencyViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class CurrencyViewHolder(private val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: Currency, clickListener: (currencyID: Long) -> Unit) {
            binding.currency = currency
            itemView.setOnClickListener {
                clickListener(1L)
            }
        }

        companion object {
            fun inflateFrom(parent: ViewGroup): CurrencyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)

                val binding = CurrencyItemBinding.inflate(layoutInflater, parent, false)
                return CurrencyViewHolder(binding)
            }
        }
    }

}