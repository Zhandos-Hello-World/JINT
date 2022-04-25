package com.example.myapplication.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentCurrencyBinding


class CurrencyFragment : Fragment() {
    private var _binding: FragmentCurrencyBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: CurrencyViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(
            this,
            CurrencyViewModelFactory()
        )[CurrencyViewModel::class.java]

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.currencies.observe(viewLifecycleOwner, Observer { it ->
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, it.map { it.key })

            binding.currency1.setAdapter(adapter)
            binding.currency2.setAdapter(adapter)
        })
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}