package com.srappetito.flybonditestmvvm.network.retrofit

import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.network.retrofit.interfaces.RetrofitServices
import retrofit2.Response

class RetrofitHelperImpl(private val retrofitServices: RetrofitServices): RetrofitHelper {

    override suspend fun getAllFlights(): Response<FlyResponse?> = retrofitServices.getAllFlies()

}