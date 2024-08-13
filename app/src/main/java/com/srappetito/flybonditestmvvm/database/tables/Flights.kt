package com.srappetito.flybonditestmvvm.database.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Flights")
data class Flights(
    @PrimaryKey(autoGenerate = true)
    var flightId: Int? = null,
    val date: String,
    val origin: String,
    val destination: String,
    val price: Double,
    val availability: Int
)
