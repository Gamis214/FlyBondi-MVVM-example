package com.srappetito.flybonditestmvvm.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.srappetito.flybonditestmvvm.adapter.FlightsAdapter
import com.srappetito.flybonditestmvvm.database.tables.Flights
import com.srappetito.flybonditestmvvm.databinding.FragmentHomeFlightsBinding
import com.srappetito.flybonditestmvvm.utils.Status
import com.srappetito.flybonditestmvvm.utils.StatusLoading
import com.srappetito.flybonditestmvvm.utils.Utils
import com.srappetito.flybonditestmvvm.viewModels.HomeFlightsViewModel
import java.util.Collections

class HomeFlights : Fragment() {

    private var _binding: FragmentHomeFlightsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeFlightsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeFlightsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initObservers()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.getDataFromDB.setOnClickListener {
            saveDataInDB(Utils.getFlightsToSaveDB(requireActivity()))
        }
        binding.btnGetDataFromServices.setOnClickListener {
            getDataFromServices()
        }
        binding.btnDeleteBD.setOnClickListener {
            deleteDataInDB()
        }
    }

    private fun initObservers(){
        viewModel.statusLoading.observe(viewLifecycleOwner){
            with(binding){
                when(it.status){
                    StatusLoading.LOADING -> {
                        if(recyclerView.isVisible) recyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    StatusLoading.DISSMISSLOADING -> {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
        viewModel.showFlights.observe(viewLifecycleOwner){
            showFlights(it)
        }
    }

    private fun saveDataInDB(lstFlights: List<Flights>){
        viewModel.saveAllFlightsInDB(lstFlights).observe(viewLifecycleOwner){
            with(binding){
                when (it.status){
                    Status.SUCCESS -> {
                        if(it.data!!){
                            txtStatusDB.text = "Success"
                            txtStatusDB.setTextColor(Color.parseColor("#33cc33"))
                        }
                    }
                    Status.ERROR -> {
                        txtStatusDB.text = "on Error to save data"
                        txtStatusDB.setTextColor(Color.parseColor("#cc3300"))
                    }
                }
            }
        }
    }

    private fun getDataFromServices(){
        viewModel.getFlightsFromServices().observe(viewLifecycleOwner){
            with(binding){
                when(it.status){
                    Status.SUCCESS -> {
                        txtStatusServices.text = "SUCCESS DATA"
                        txtStatusServices.setTextColor(Color.parseColor("#33cc33"))
                        saveDataInDB(it.data!!.flights.listFlights)
                    }
                    Status.ERROR -> {
                        txtStatusServices.text = "${it.message}"
                        txtStatusServices.setTextColor(Color.parseColor("#cc3300"))
                    }
                }
            }
        }
    }

    private fun showFlights(){
        viewModel.getAllFlightsInDB().observe(viewLifecycleOwner){
            with(binding){
                when(it.status){
                    Status.SUCCESS -> {
                        recyclerView.adapter = FlightsAdapter(it.data!!)
                        if(!recyclerView.isVisible) recyclerView.visibility = View.VISIBLE
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        txtStatusServices.text = it.message
                    }
                }
            }
        }
    }

    private fun showFlights(lstFlights: List<Flights>){
        with(binding){
            if(lstFlights.isNotEmpty()){
                recyclerView.adapter = FlightsAdapter(lstFlights)
                if(!recyclerView.isVisible) recyclerView.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.GONE
                Toast.makeText(requireActivity(),"EMPTY FLIGHTS LIST",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteDataInDB(){
        viewModel.deleteAllDataFromDB().observe(viewLifecycleOwner){
            with(binding){
                when(it.status){
                    Status.SUCCESS ->{
                        Toast.makeText(requireActivity(),"SUCCESS DELETE TABLE",Toast.LENGTH_SHORT).show()
                        txtStatusDB.text = "Status"
                        txtStatusDB.setTextColor(Color.parseColor("#000000"))
                    }
                    Status.ERROR -> {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}