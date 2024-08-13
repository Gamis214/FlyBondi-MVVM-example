package com.srappetito.flybonditestmvvm.network.retrofit.interfaces

import com.srappetito.flybonditestmvvm.models.FlyResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RetrofitServices {

    @Headers("X-Access-Key: \$2a\$10\$cmbPTnC25e3ZpbAHP6BvzOtVkwObDpN8rkhdFSSLodlxvUz6pHYHi")
    @GET("v3/b/66bb8712acd3cb34a8740b11")
    suspend fun getAllFlies(): FlyResponse

}