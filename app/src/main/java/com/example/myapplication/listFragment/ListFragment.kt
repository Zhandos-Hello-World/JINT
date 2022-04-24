package com.example.myapplication.listFragment

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.StartActivity
import com.example.myapplication.databinding.FragmentListBinding
import com.example.myapplication.repository.CurrencyRepository
import com.example.myapplication.request.NetworkService
import com.example.myapplication.room.Currency
import com.example.myapplication.room.CurrencyDatabase


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = activity as AppCompatActivity
        val adapter = CurrencyAdapter {
        }

        val apiInterface = NetworkService.getInstance().getJSONApi()
        val database = CurrencyDatabase.getInstance(application)
        val currencyRepository = CurrencyRepository(apiInterface, database)

        viewModel = ViewModelProvider(
            this,
            ListViewModelFactory(currencyRepository)
        )[ListViewModel::class.java]

        viewModel.getAll.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


        application.setSupportActionBar(binding.toolbar)


        binding.currencyList.adapter = adapter
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.sync -> true
        R.id.date -> true
        else -> false
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}