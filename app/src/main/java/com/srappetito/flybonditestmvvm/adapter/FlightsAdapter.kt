package com.srappetito.flybonditestmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.srappetito.flybonditestmvvm.database.tables.Flights
import com.srappetito.flybonditestmvvm.databinding.ItemFlightsBinding

class FlightsAdapter(private val lstFlights: List<Flights>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemHolder(ItemFlightsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ItemHolder).bindView()
    }

    inner class ItemHolder(val binding: ItemFlightsBinding): ViewHolder(binding.root){
        fun bindView(){
            with(binding){
                txtCount.text = (adapterPosition + 1).toString()
                txtData.text = lstFlights[adapterPosition].data
                txtPrice.text = lstFlights[adapterPosition].price.toString()
                txtOrigin.text = lstFlights[adapterPosition].origin
                txtDestination.text = lstFlights[adapterPosition].destination
                txtAvailability.text = lstFlights[adapterPosition].availability.toString()
            }
        }
    }

    override fun getItemCount(): Int = lstFlights.size

}