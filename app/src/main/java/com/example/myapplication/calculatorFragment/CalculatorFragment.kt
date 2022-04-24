package com.example.myapplication.calculatorFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentCalculatorBinding
import com.example.myapplication.room.CurrencyDatabase

class CalculatorFragment : Fragment() {
    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        val view = binding.root

        val application = requireNotNull(this.activity)

        val dao = CurrencyDatabase.getInstance(application).currencyDao

        val factory = CalculatorViewModelFactory(dao, 0L)
        val viewModel = ViewModelProvider(this, factory)[CalculatorViewModel::class.java]


        viewModel.rub.observe(viewLifecycleOwner) { value ->
            if (binding.rubEditText.hasFocus()) {
                if (value.isEmpty()) {
                    viewModel.rub.value = "0"
                } else {
                    viewModel.current.value = viewModel.rubToCurr(value)
                }
            }

        }
        viewModel.current.observe(viewLifecycleOwner) { value ->
            if (binding.currentEditText.hasFocus()) {
                if (value.isEmpty()) {
                    viewModel.current.value = "0"
                } else {
                    viewModel.rub.value = viewModel.currToRub(value)
                }
            }
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.currentBtn.setOnClickListener {
            val navController = view.findNavController()
            val action = CalculatorFragmentDirections.actionCalculatorFragmentToListFragment()
            navController.navigate(action)
        }

        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}