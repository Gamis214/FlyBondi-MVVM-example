package com.srappetito.flybonditestmvvm.network.retrofit

import com.srappetito.flybonditestmvvm.models.FlyResponse
import retrofit2.Response

interface RetrofitHelper {

    suspend fun getAllFlights(): Response<FlyResponse?>

}