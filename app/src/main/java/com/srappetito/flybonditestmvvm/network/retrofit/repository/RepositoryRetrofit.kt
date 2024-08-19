package com.srappetito.flybonditestmvvm.network.retrofit.repository

import com.srappetito.flybonditestmvvm.models.FlyResponse
import com.srappetito.flybonditestmvvm.network.retrofit.RetrofitHelper
import com.srappetito.flybonditestmvvm.network.retrofit.interfaces.RetrofitServices
import com.srappetito.flybonditestmvvm.utils.BaseApiResponse
import com.srappetito.flybonditestmvvm.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryRetrofit @Inject constructor(private val retrofitServices: RetrofitServices): BaseApiResponse(), RetrofitHelper {

    override suspend fun getFlights(): NetworkResult<FlyResponse?> {
        return safeApiCall {
            retrofitServices.getAllFlies()
        }
    }

}