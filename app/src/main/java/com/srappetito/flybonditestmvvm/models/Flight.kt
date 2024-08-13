package com.srappetito.flybonditestmvvm.models

data class Flight(
    val data: String,
    val origin: String,
    val destination: String,
    val price: Double,
    val availability: Int
)
