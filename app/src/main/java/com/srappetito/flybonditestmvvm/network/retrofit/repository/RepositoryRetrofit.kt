package com.srappetito.flybonditestmvvm.network.retrofit.repository

import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.network.retrofit.RetrofitHelperImpl
import com.srappetito.flybonditestmvvm.utils.BaseApiResponse
import com.srappetito.flybonditestmvvm.utils.NetworkResult

class RepositoryRetrofit(private val retrofitHelperImpl: RetrofitHelperImpl): BaseApiResponse() {

    suspend fun getFlights(): NetworkResult<FlyResponse?>{
        return safeApiCall {
            retrofitHelperImpl.getAllFlights()
        }
    }

}