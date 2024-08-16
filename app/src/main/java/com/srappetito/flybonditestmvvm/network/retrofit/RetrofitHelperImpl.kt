package com.srappetito.flybonditestmvvm.network.retrofit

import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.network.retrofit.interfaces.RetrofitServices
import retrofit2.Response
import javax.inject.Inject

class RetrofitHelperImpl @Inject constructor(private val retrofitServices: RetrofitServices): RetrofitHelper {

    override suspend fun getAllFlights(): Response<FlyResponse?> = retrofitServices.getAllFlies()

}