package com.srappetito.flybonditestmvvm.network.retrofit

import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.network.retrofit.interfaces.RetrofitServices

class RetrofitHelperImpl(private val retrofitServices: RetrofitServices): RetrofitHelper {

    override suspend fun getAllFlights(): FlyResponse = retrofitServices.getAllFlies()

}