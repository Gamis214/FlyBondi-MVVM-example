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
import com.srappetito.flybonditestmvvm.databinding.FragmentHomeFlightsBinding
import com.srappetito.flybonditestmvvm.utils.Status
import com.srappetito.flybonditestmvvm.utils.StatusLoading
import com.srappetito.flybonditestmvvm.utils.Utils
import com.srappetito.flybonditestmvvm.viewModels.HomeFlightsViewModel

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
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.getDataFromDB.setOnClickListener {
            saveDataInDB()
        }
        binding.btnGetDataFromServices.setOnClickListener {
            getDataFromServices()
        }
        initProgressObserver()
    }

    private fun initProgressObserver(){
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
    }

    private fun saveDataInDB(){
        viewModel.saveAllFlightsInDB(Utils.getFlightsToSaveDB(requireActivity())).observe(viewLifecycleOwner){
            with(binding){
                when (it.status){
                    Status.SUCCESS -> {
                        if(it.data!!){
                            txtStatusDB.text = "Success SAVING data"
                            txtStatusDB.setTextColor(Color.parseColor("#33cc33"))
                        } else {
                            txtStatusDB.text = "Is data in DB"
                            txtStatusDB.setTextColor(Color.parseColor("#33cc33"))
                        }
                        showFlights()
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
                        Toast.makeText(requireActivity(),"SUCCESS",Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        txtStatusServices.text = "${it.message}"
                        txtStatusServices.setTextColor(Color.parseColor("#cc3300"))
                        Toast.makeText(requireActivity(),"ERROR",Toast.LENGTH_SHORT).show()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}