package com.srappetito.flybonditestmvvm.models

import com.google.gson.annotations.SerializedName

data class FlyResponse(
    @SerializedName("record")
    val flights: Flights
)
