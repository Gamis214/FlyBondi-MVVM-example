package com.srappetito.flybonditestmvvm.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.srappetito.flybonditestmvvm.databinding.FragmentHomeFlightsBinding
import com.srappetito.flybonditestmvvm.utils.Status
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
        binding.getDataFromDB.setOnClickListener {
            saveDataInDB()
        }
        binding.btnGetDataFromServices.setOnClickListener {
            getDataFromServices()
        }
    }

    private fun saveDataInDB(){
        viewModel.saveAllFlightsInDB(Utils.getFlightsToSaveDB(requireActivity())).observe(viewLifecycleOwner){
            with(binding){
                when (it.status){
                    Status.LOADING -> {
                        txtStatusDB.text = "Get & Saving Data..."
                        txtStatusDB.setTextColor(Color.parseColor("#0000ff"))
                    }
                    Status.SUCCESS -> {
                        if(it.data!!){
                            txtStatusDB.text = "Success SAVING data"
                            txtStatusDB.setTextColor(Color.parseColor("#33cc33"))
                        } else {
                            txtStatusDB.text = "Is data in DB"
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
                if(it != null){
                    Toast.makeText(requireActivity(),"SUCCESS",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireActivity(),"ERROR",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}