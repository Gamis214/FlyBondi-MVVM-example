package com.srappetito.flybonditestmvvm.repository

import androidx.lifecycle.MutableLiveData
import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.network.retrofit.RetrofitBuilder

class HomeFlightsRepository {

    suspend fun getAllFlights(): FlyResponse{
        return RetrofitBuilder.retrofitServices.getAllFlies()
    }

}