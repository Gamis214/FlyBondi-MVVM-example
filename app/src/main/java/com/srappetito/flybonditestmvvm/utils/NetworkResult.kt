package com.srappetito.flybonditestmvvm.utils

import retrofit2.Response

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
}

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessage")
}