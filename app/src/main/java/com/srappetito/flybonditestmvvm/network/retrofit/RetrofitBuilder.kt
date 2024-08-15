package com.srappetito.flybonditestmvvm.network.retrofit

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.srappetito.flybonditestmvvm.network.retrofit.interfaces.RetrofitServices
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val BASE_URL = "https://api.jsonbin.io"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createHttpClient())
            .build()
    }

    private fun createHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(120,TimeUnit.SECONDS)
            .readTimeout(60,TimeUnit.SECONDS)
            .writeTimeout(60,TimeUnit.SECONDS)
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .build()
            ).build()

    }

    val retrofitServices: RetrofitServices = getRetrofit().create(RetrofitServices::class.java)

}