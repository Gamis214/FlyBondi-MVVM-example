package com.srappetito.flybonditestmvvm.network.retrofit

import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.utils.NetworkResult

interface RetrofitHelper {

    suspend fun getFlights(): NetworkResult<FlyResponse?>

}