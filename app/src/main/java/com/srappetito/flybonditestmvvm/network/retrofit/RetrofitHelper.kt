package com.srappetito.flybonditestmvvm.network.retrofit

import com.srappetito.flybonditestmvvm.models.FlyResponse

interface RetrofitHelper {

    suspend fun getAllFlights(): FlyResponse

}